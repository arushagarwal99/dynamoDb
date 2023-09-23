package com.thrivent.core.repository;

public interface KeyMapper<I, K> {

    I map(K key);
}
