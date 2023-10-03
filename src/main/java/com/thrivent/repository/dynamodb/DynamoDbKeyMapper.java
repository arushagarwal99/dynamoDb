package com.thrivent.repository.dynamodb;

import com.thrivent.repository.base.KeyMapper;
import software.amazon.awssdk.enhanced.dynamodb.Key;

public interface DynamoDbKeyMapper<T, B, K> extends KeyMapper<Key, K> {

    String primaryPartitionKeyGetter(T entity);

    void primaryPartitionKeySetter(B entityBuilder, String value);

    String primarySortKeyGetter(T entity);

    void primarySortKeySetter(B entityBuilder, String value);

    String secondaryPartitionKeyGetter(T entity);

    void secondaryPartitionKeySetter(B entityBuilder, String value);

    String secondarySortKeyGetter(T entity);

    void secondarySortKeySetter(B entityBuilder, String value);

}
