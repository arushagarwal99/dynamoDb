package com.thrivent.core.repository;

public interface CrudRepository<T, K> {

    long count();

    boolean existsById(K id);

    Iterable<T> readAll();

    T getById(K id);

    T create(T entity);

    T update(T entity);

    void deleteAll();

    T deleteById(K id);

    T delete(T entity);
}
