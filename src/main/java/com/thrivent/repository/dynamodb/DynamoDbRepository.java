package com.thrivent.repository.dynamodb;

import com.thrivent.repository.dynamodb.model.MetaData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;

public class DynamoDbRepository implements Repository {

    private static final Logger log = LoggerFactory.getLogger(DynamoDbRepository.class);

    private final DynamoDbClient dynamoDbClient;
    private final String tableName;
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    private final TableSchema<MetadataTableItem> metaDataTableSchema;
    private final DynamoDbTable<MetadataTableItem> metaDataTable;
    private final DynamoDbItemMapper mapper = DynamoDbItemMapper.INSTANCE;
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
    //@Override
    public void insertCustomer(MetaData metaData) {


        log.info("Inserting data=: {}", metaData);
        metaDataTable.putItem(mapper.mapToItem(metaData));
//        var expression = Expression.builder()
//                .expression("attribute_not_exists(PK)")
//                .build();
//        var putItemEnhancedRequest = PutItemEnhancedRequest.builder(MetadataTableItem.class)
//                .item(metaData)
//                .build();
//        try {
//            this.metaDataTable.putItem(putItemEnhancedRequest);
//        } catch (ConditionalCheckFailedException e) {
//            throw new DynamoDbEntityAlreadyExistsException("Attempted to overwrite an item which already exists with PK=" + metaData.getPartitionKey());
//        }
    }

   // @Override
    public MetadataTableItem getCustomerById(String partitionKey, String sortKey) {
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

    Map<String, AttributeValue> getCustomerByIdDynamoDbJson(String partitionKey, String sortKey) {
        var pk = AttributeValue.builder().s(partitionKey).build();
        var sk = AttributeValue.builder().s(sortKey).build();
        var getItemRequest = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of("PK", pk, "SK", sk))
                .build();
        GetItemResponse item = dynamoDbClient.getItem(getItemRequest);
        return item.item();
    }

    DynamoDbTable<MetadataTableItem> getCustomerTable() {
        return this.metaDataTable;
    }



}
