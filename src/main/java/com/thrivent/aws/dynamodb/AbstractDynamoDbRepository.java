package com.thrivent.aws.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

public abstract class AbstractDynamoDbRepository<T, B, K> {

    protected final DynamoDbEnhancedClient client;

    private DynamoDbTable<T> table;

    public AbstractDynamoDbRepository(
            DynamoDbEnhancedClient client) {
        this.client = client;
    }

    protected final DynamoDbTable<T> table() {
        if (table == null) {
            table = client.table(tableName(), schemaBuilder().build());
        }
        return table;
    }

    protected abstract String tableName();

    protected abstract KeyMapper<K> keyMapper();

    protected abstract TableSchemaBuilder<T, B> schemaBuilder();
}
