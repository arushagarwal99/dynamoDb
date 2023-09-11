package com.thrivent.repository.dynamodb;

import com.thrivent.repository.dynamodb.model.MetaData;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

public class Main {
    public static void main(String[] args)
    {
        DynamoDbClient client =  DynamoDbClient.builder()
                //.endpointOverride(URI.create("http://localhost:8000"))
                // The region is meaningless for local DynamoDb but required for client builder validation
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("", "")))
                .build();
        DynamoDbRepository db = new DynamoDbRepository(client, "metadata");
        db.createTableIfNotExists();
        db.insertCustomer(new MetaData("p#1","s#1","v"));


    }
}
