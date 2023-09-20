package com.thrivent.aws.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.Key;

public interface KeyMapper<K> {

    Key map(K key);
}
