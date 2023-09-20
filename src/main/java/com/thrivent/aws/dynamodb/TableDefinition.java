package com.thrivent.aws.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

public interface TableDefinition<T, K> {

    String name();

    KeyMapper<K> keyMapper();

    TableSchema<T> schema();
}
