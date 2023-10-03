package com.thrivent.repository.base;

public interface KeyMapper<I, K> {

    I map(K key);
}
