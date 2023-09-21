package com.thrivent.aws.dynamodb;

import dagger.Module;
import dagger.Provides;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.inject.Singleton;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URI;

@Module
public class DynamoDbLocalModule {

    private static final String ACCESS_KEY_ID = "dummy-access-key";
    private static final String SECRET_ACCESS_KEY = "dummy-secret";
    private static final int DEFAULT_PORT = 4566;
    private static final int PORT;

    static {
        int port = 0;
        try (final ServerSocket serverSocket = new ServerSocket(0)) {
            port = serverSocket.getLocalPort();
        } catch (IOException ignored) {
        }
        PORT = port > 0 ? port : DEFAULT_PORT;
    }

    @Provides
    @Singleton
    DynamoDbClient provideDynamoDbClient() {
        final String endpoint = String.format("http://localhost:%d", PORT);

//        System.setProperty("sqlite4java.library.path", "target/sqlite4java-native-libs");

        return DynamoDbClient.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.US_EAST_1)
//                .credentialsProvider(
//                        StaticCredentialsProvider.create(
//                                AwsBasicCredentials.create(ACCESS_KEY_ID, SECRET_ACCESS_KEY)))
                .build();
    }

    @Provides
    @Singleton
    public DynamoDbEnhancedClient provideDynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder().dynamoDbClient(dynamoDbClient).build();
    }

    @Provides
    @Singleton
    DynamoDbLocal provideDynamoDbLocal() {
        return DynamoDbLocal.create(PORT);
    }
}
