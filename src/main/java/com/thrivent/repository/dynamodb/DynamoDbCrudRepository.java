package com.thrivent.repository.dynamodb;

import com.thrivent.repository.base.CrudRepository;
import org.apache.commons.lang3.NotImplementedException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;

public abstract class DynamoDbCrudRepository<T, B, K>
        extends AbstractDynamoDbRepository<T, B, K>
        implements CrudRepository<T, K> {

    public DynamoDbCrudRepository(
            DynamoDbEnhancedClient client,
            DynamoDbKeyMapper<T, B, K> keyMapper,
            TableSchemaBuilder<T, B> schemaBuilder) {
        super(client, keyMapper, schemaBuilder);
    }

    @Override
    public boolean existsById(K id) {
        final T item = getById(id);
        return item != null;
    }

    @Override
    public T getById(K id) {
        final Key key = keyMapper.map(id);
        return table.getItem(key);
    }

    @Override
    public T create(T entity) {
        table.putItem(entity);
        return entity;
    }

    @Override
    public T update(T t) {
        return table.updateItem(t);
    }

    @Override
    public Iterable<T> readAll() {
        throw new NotImplementedException();
    }

    @Override
    public void deleteAll() {
        throw new NotImplementedException();
    }

    @Override
    public T deleteById(K k) {
        throw new NotImplementedException();
    }

    @Override
    public T delete(T t) {
        throw new NotImplementedException();
    }

    @Override
    public long count() {
        throw new NotImplementedException();
    }
}
