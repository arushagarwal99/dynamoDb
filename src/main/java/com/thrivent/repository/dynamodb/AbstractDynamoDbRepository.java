package com.thrivent.repository.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

public abstract class AbstractDynamoDbRepository<T, B, K> {

    protected final DynamoDbTable<T> table;

    protected final DynamoDbKeyMapper<T, B, K> keyMapper;

    public AbstractDynamoDbRepository(
            DynamoDbEnhancedClient client,
            DynamoDbKeyMapper<T, B, K> keyMapper,
            TableSchemaBuilder<T, B> schemaBuilder) {
        this.keyMapper = keyMapper;
        this.table = client.table(schemaBuilder.tableName(), schemaBuilder.build());
    }
}
