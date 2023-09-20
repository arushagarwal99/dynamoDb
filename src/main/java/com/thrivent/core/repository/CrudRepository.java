package org.example.core.repository;

import java.util.Optional;

public interface CrudRepository<T, K> {

    long count();

    boolean existsById(K id);

    Iterable<T> findAll();

    Optional<T> findById(K id);

    T getById(K id);

    T create(T entity);

    T update(T entity);

    void deleteAll();

    T deleteById(K id);

    T delete(T entity);
}
