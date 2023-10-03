package com.thrivent.repository.dynamodb;

import org.apache.commons.lang3.NotImplementedException;

public abstract class AbstractDynamoDbKeyMapper<T, B, K> implements DynamoDbKeyMapper<T, B, K> {

    @Override
    public String secondaryPartitionKeyGetter(T entity) {
        throw new NotImplementedException("No implementation provided to get secondary partitionKey from entity.");
    }

    @Override
    public void secondaryPartitionKeySetter(B entityBuilder, String value) {
        throw new NotImplementedException("No implementation provided to set secondary partitionKey into entity.");
    }

    @Override
    public String  secondarySortKeyGetter(T entity) {
        throw new NotImplementedException("No implementation provided to get secondary sortKey from entity.");
    }

    @Override
    public void secondarySortKeySetter(B entityBuilder, String value) {
        throw new NotImplementedException("No implementation provided to set secondary sortKey into entity.");
    }
}
