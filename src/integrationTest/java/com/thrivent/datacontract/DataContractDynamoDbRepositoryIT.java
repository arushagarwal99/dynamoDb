package com.thrivent.datacontract;

import com.thrivent.aws.dynamodb.DynamoDbLocal;
import com.thrivent.aws.dynamodb.DynamoDbLocalModule;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class DataContractDynamoDbRepositoryIT {

    @Singleton
    @Component(modules = {DynamoDbLocalModule.class})
    interface Provider {

        DynamoDbLocal dynamoDbLocal();

        DynamoDbClient dynamoDbClient();

        DynamoDbEnhancedClient dynamoDbEnhancedClient();

        DataContractDynamoDbRepository dataContractDynamoDbRepository();
    }

    private static Provider provider;

    private DynamoDbClient client;

    private DynamoDbTable<DataContract> table;

    private DataContractDynamoDbRepository repository;

    @BeforeAll
    public static void setupClass() {
        provider = DaggerDataContractDynamoDbRepositoryIT_Provider.create();
        provider.dynamoDbLocal().start();
    }

    @AfterAll
    public static void teardownClass() {
        provider.dynamoDbLocal().stop();
    }

    @BeforeEach
    public void setupTest() {
        client = provider.dynamoDbClient();
        repository = provider.dataContractDynamoDbRepository();

        // Create new table for each test
        final DynamoDbEnhancedClient enhancedClient = provider.dynamoDbEnhancedClient();
        table = enhancedClient.table(repository.tableName(), repository.schemaBuilder().build());
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
                .tableName(repository.tableName())
                .build();
        final ScanResponse response = client.scan(request);
        
        assertThat(response.count(), equalTo(1));

        final Map<String, AttributeValue> item = response.items().get(0);
        assertThat(item.get("partitionKey").s(), equalTo(expectedPartitionKey));
        assertThat(item.get("sortKey").s(), equalTo(expectedSortKey));
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
        assertThat(stored, samePropertyValuesAs(given, "createdAt", "updatedAt"));
    }
}
