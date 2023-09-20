package org.example.aws.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import javax.inject.Provider;

public abstract class AbstractDynamoDbRepository<T, B, K> {

    protected final Provider<DynamoDbEnhancedClient> client;

    private DynamoDbTable<T> table;

    public AbstractDynamoDbRepository(
            Provider<DynamoDbEnhancedClient> client) {
        this.client = client;
    }

    protected final DynamoDbTable<T> table() {
        if (table == null) {
            table = client.get().table(tableName(), schemaBuilder().build());
        }
        return table;
    }

    protected abstract String tableName();

    protected abstract KeyMapper<K> keyMapper();

    protected abstract TableSchemaBuilder<T, B> schemaBuilder();
}
