package com.thrivent.datacontract;

import com.thrivent.aws.dynamodb.DynamoDbLocal;
import com.thrivent.aws.dynamodb.DynamoDbLocalModule;
import com.thrivent.service.datacontract.*;
import dagger.Component;
import org.junit.jupiter.api.*;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import javax.inject.Singleton;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DataContractDynamoDbRepositoryIT {

    @Singleton
    @Component(modules = {DynamoDbLocalModule.class})
    interface Provider {

        DynamoDbLocal dynamoDbLocal();

        DynamoDbClient dynamoDbClient();

        DynamoDbEnhancedClient dynamoDbEnhancedClient();

        DataContractTableSchema dataContractTableSchema();

        DataContractDynamoDbRepository dataContractDynamoDbRepository();
    }

    private static Provider provider;

    private static DynamoDbClient client;

    private static DataContractTableSchema schema;

    private static DataContractDynamoDbRepository repository;

    private static DynamoDbTable<DataContract> table;

    @BeforeAll
    public static void setupClass() {
        provider = DaggerDataContractDynamoDbRepositoryIT_Provider.create();
        provider.dynamoDbLocal().start();

        client = provider.dynamoDbClient();
        repository = provider.dataContractDynamoDbRepository();
        schema = provider.dataContractTableSchema();
        final DynamoDbEnhancedClient enhancedClient = provider.dynamoDbEnhancedClient();
        table = enhancedClient.table(schema.tableName(), schema.build());
    }

    @AfterAll
    public static void teardownClass() {
        provider.dynamoDbLocal().stop();
    }

    @BeforeEach
    public void setupTest() {
        table.createTable();
    }

    @AfterEach
    public void teardownTest() {
        table.deleteTable();
    }

    @Test
    public void givenDataContract_whenCalledCreate_thenItemIsCreated() {
        final String name = "credit-card-data-contract";
        final DataContract dataContract = ImmutableDataContract.builder()
                .name(name)
                .domain("credit")
                .description("DataContract for CreditCard in Credit Domain.")
                .protocolVersion("1")
                .type(DataContract.Type.INGESTION)
                .owner("tdp")
                .schedule("5 0 * 8 *")
                .retentionPeriod(10)
                .addEmailNotifications("john@example.com", "jane@example.com")
                .build();
        final String expectedPartitionKey = "DC#" + name;
        final String expectedSortKey = "LATEST";

        repository.create(dataContract);

        final ScanRequest request = ScanRequest.builder()
                .tableName(schema.tableName())
                .build();
        final ScanResponse response = client.scan(request);

        assertThat(response.count()).isEqualTo(1);

        final Map<String, AttributeValue> item = response.items().get(0);
        assertThat(item.get("partitionKey").s()).isEqualTo(expectedPartitionKey);
        assertThat(item.get("sortKey").s()).isEqualTo(expectedSortKey);
    }

    @Test
    public void givenDataContract_whenCalledCreateAndGet_thenDataContractMatches() {
        final String name = "credit-card-data-contract";
        final DataContract given = ImmutableDataContract.builder()
                .name(name)
                .domain("credit")
                .description("DataContract for CreditCard in Credit Domain.")
                .protocolVersion("1")
                .type(DataContract.Type.INGESTION)
                .owner("tdp")
                .schedule("5 0 * 8 *")
                .retentionPeriod(10)
                .addEmailNotifications("john@example.com", "jane@example.com")
                .build();

        repository.create(given);

        final DataContractKey key = ImmutableDataContractKey.builder().name(name).build();
        final DataContract stored = repository.getById(key);
        assertThat(stored).usingRecursiveComparison()
                        .ignoringFields("createdAt", "updatedAt")
                        .isEqualTo(given);
    }

    @Test
    public void givenExistingDataContract_whenCalledUpdateAndGet_thenDataContractMatches() {
        final String name = "credit-card-data-contract";
        final DataContract given = ImmutableDataContract.builder()
                .name(name)
                .domain("credit")
                .description("DataContract for CreditCard in Credit Domain.")
                .protocolVersion("1")
                .type(DataContract.Type.INGESTION)
                .owner("tdp")
                .schedule("5 0 * 8 *")
                .retentionPeriod(10)
                .addEmailNotifications("john@example.com", "jane@example.com")
                .build();
        final DataContract existing = repository.create(given);

        final DataContract toBeUpdated = ImmutableDataContract.builder()
                .from(existing)
                .protocolVersion("2")
                .schedule("5 4 0 0 0")
                .retentionPeriod(15)
                .build();

        final DataContract updated = repository.update(toBeUpdated);
        System.out.println(updated);
        System.out.println(toBeUpdated);

        assertThat(updated).usingRecursiveComparison()
                .ignoringFields("createdAt", "updatedAt")
                .isEqualTo(toBeUpdated);
    }
}
