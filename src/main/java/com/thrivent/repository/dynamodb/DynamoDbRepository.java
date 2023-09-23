package com.thrivent.repository.dynamodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class DynamoDbRepository  {

    private static final Logger log = LoggerFactory.getLogger(DynamoDbRepository.class);

    private final DynamoDbClient dynamoDbClient;
    private final String tableName;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    private final TableSchema<MetadataTableItem> metaDataTableSchema;
    private final DynamoDbTable<MetadataTableItem> metaDataTable;

    public DynamoDbRepository(DynamoDbClient dynamoDbClient, String tableName)
    {
        this.dynamoDbClient = dynamoDbClient;
        this.tableName = tableName;
        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.metaDataTableSchema = TableSchema.fromClass(MetadataTableItem.class);
        this.metaDataTable = dynamoDbEnhancedClient.table(tableName, metaDataTableSchema);
    }
    public DynamoDbRepository()
    {
        this.tableName = "tdp-datacontract-sandbox";
        this.dynamoDbClient = getDynamoDbClient();
        this.dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();

        this.metaDataTableSchema = TableSchema.fromClass(MetadataTableItem.class);
        this.metaDataTable = dynamoDbEnhancedClient.table(tableName, metaDataTableSchema);
    }
    public DynamoDbClient getDynamoDbClient() {
        AwsCredentialsProvider credentialsProvider =
                DefaultCredentialsProvider.builder()
                        .build();

        return DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider).build();
    }
    //@Override
    public void insertData(MetadataTableItem metadataTableItem) {
        log.info("Inserting data=: {}", metadataTableItem);
        metaDataTable.putItem(metadataTableItem);

    }

   // @Override
    public MetadataTableItem getDataByPartitionKeyAndSortKey(String partitionKey, String sortKey) {
        var key = Key.builder().partitionValue(partitionKey).sortValue(sortKey).build();
        return this.metaDataTable.getItem(key);
    }
    static CreateTableRequest createTableRequest(String tableName) {
        var pk = KeySchemaElement.builder().attributeName("PK").keyType(KeyType.HASH).build();
        var pkDef = AttributeDefinition.builder().attributeName("PK").attributeType(ScalarAttributeType.S).build();
        var sk = KeySchemaElement.builder().attributeName("SK").keyType(KeyType.RANGE).build();
        var skDef = AttributeDefinition.builder().attributeName("SK").attributeType(ScalarAttributeType.S).build();
        return CreateTableRequest.builder()
                .tableName(tableName)
                .keySchema(pk, sk)
                .attributeDefinitions(pkDef, skDef)
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();
    }

    public void createTableIfNotExists() {
        if (tableExists(this.tableName)) {
            log.info("Table={} already exists", this.tableName);
        } else {
            log.info("Creating table={}", this.tableName);
            dynamoDbClient.createTable(createTableRequest(this.tableName));
        }
    }

    boolean tableExists(String tableName) {
        try {
            dynamoDbClient.describeTable(r -> r.tableName(tableName));
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }

    void getDataByPartitionKey(String partitionKey) {
    }

    DynamoDbTable<MetadataTableItem> getCustomerTable() {
        return this.metaDataTable;
    }



}
