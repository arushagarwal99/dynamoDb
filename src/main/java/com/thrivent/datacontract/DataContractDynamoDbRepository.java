package com.thrivent.datacontract;

import com.thrivent.aws.dynamodb.DynamoDbCrudRepository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

import javax.inject.Inject;

public class DataContractDynamoDbRepository
        extends DynamoDbCrudRepository<DataContract, ImmutableDataContract.Builder, DataContractKey>
        implements DataContractRepository {

    @Inject
    public DataContractDynamoDbRepository(
            DynamoDbEnhancedClient client,
            DataContractKeyMapper keyMapper,
            DataContractTableSchema schema) {
        super(client, keyMapper, schema);
    }
}
