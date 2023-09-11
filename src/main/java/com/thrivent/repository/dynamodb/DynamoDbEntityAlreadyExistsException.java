package com.thrivent.repository.dynamodb;

public class DynamoDbEntityAlreadyExistsException extends RuntimeException {
    public DynamoDbEntityAlreadyExistsException(String message) {
        super(message);
    }
}