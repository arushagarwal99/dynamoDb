package com.thrivent.datacontract;

import com.thrivent.aws.dynamodb.DynamoDbCrudRepository;
import com.thrivent.aws.dynamodb.KeyMapper;
import com.thrivent.aws.dynamodb.TableSchemaBuilder;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

public class DataContractDynamoDbRepository  extends DynamoDbCrudRepository<DataContract, ImmutableDataContract.Builder, DataContractKey> implements DataContractRepository {

    public DataContractDynamoDbRepository(DynamoDbEnhancedClient client) {
        super(client);
    }

    @Override
    protected String tableName() {
        return "tdp-datacontract-sandbox";
    }

    @Override
    protected KeyMapper<DataContractKey> keyMapper() {
        return new DataContractKeyMapper();
    }

    @Override
    protected TableSchemaBuilder<DataContract, ImmutableDataContract.Builder> schemaBuilder() {
        return new DataContractTableSchema();
    }
}
