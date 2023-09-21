package com.thrivent.aws.dynamodb;

import com.thrivent.core.repository.CrudRepository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;

public abstract class DynamoDbCrudRepository<T, B, K>
        extends AbstractDynamoDbRepository<T, B, K>
        implements CrudRepository<T, K> {

    public DynamoDbCrudRepository(DynamoDbEnhancedClient client) {
        super(client);
    }

    @Override
    public boolean existsById(K id) {
        final Key key = keyMapper().map(id);
        final T item = (T) table().getItem(key);
        return item != null;
    }

    @Override
    public T getById(K id) {
        final Key key = keyMapper().map(id);
        final T item = (T) table().getItem(key);
        return item;
    }

    @Override
    public T create(T entity) {
        table().putItem(entity);
        return entity;
    }

    @Override
    public T update(T t) {
        return (T) table().updateItem(t);
    }

    @Override
    public Iterable<T> readAll() {
        throw new RuntimeException("Not supported Now");
    }

    @Override
    public void deleteAll() {
        throw new RuntimeException("Not supported Now");
    }

    @Override
    public T deleteById(K k) {
        throw new RuntimeException("Not supported Now");
    }

    @Override
    public T delete(T t) {
        throw new RuntimeException("Not supported Now");
    }

    @Override
    public long count() {
        throw new RuntimeException("Not supported Now");
    }
}
