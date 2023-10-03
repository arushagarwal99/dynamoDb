package com.thrivent.service.datacontract;

import com.thrivent.repository.dynamodb.DynamoDbCrudRepository;
import com.thrivent.datacontract.ImmutableDataContract;
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
