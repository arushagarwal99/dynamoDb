package com.thrivent.repository.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public interface Repository {

    class Factory{
        public static Repository create(DynamoDbClient dynamoDbClient, String tableName)
        {
            return null;
        }
    }
}
