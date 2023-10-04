package com.thrivent;

//import DataContract;
//import DataContractDynamoDbRepository;
//import com.thrivent.service.datacontract.ImmutableDataContractKey;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
//import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;
//
//import java.net.URI;

public class App {

    public static void main(String[] args) {
//        final String ACCESS_KEY = "test";
//        final String SECRET_KEY = "test";
//
//        // Creating the AWS Credentials provider, using the above access and secret keys.
//        AwsCredentialsProvider credentials = StaticCredentialsProvider.create(
//                AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY));
//
//        // Selected region.
//        Region region = Region.AP_SOUTH_1;
//
//        // Creating the dynamoDB client using the credentials, the specific region and a LocalStack endpoint.
//        DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
//                .region(region)
//                .credentialsProvider(
//                        credentials)
//                .endpointOverride(URI.create("https://localhost.localstack.cloud:4566"))
//                .build();
//
//        // Creating an enhanced client, which provides additional actions to the plain client.
//        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
//                .dynamoDbClient(dynamoDbClient)
//                .build();
//
//        ListTablesResponse response = dynamoDbClient.listTables();
//        response.tableNames().forEach(System.out::println);
//
//        DataContractDynamoDbRepository repository = new DataContractDynamoDbRepository(enhancedClient);
//
//        String n1 = "credit-card-data-contract";
//        String n2 = "debit-card-data-contract";
//        String n3 = "amex-card-data-contract";

//        DataContract dc = ImmutableDataContract.builder()
//                .name(n3)
//                .domain("credit")
//                .description("credit domain amex-card data-contract")
//                .build();
//
//        repository.create(dc);
//
//        DataContract dc1 = repository.getById(ImmutableDataContractKey.builder().name(n1).build());
//        System.out.println(dc1);
//
//        DataContract dc2 = repository.getById(ImmutableDataContractKey.builder().name(n2).build());
//        System.out.println(dc2);
//
//        DataContract dc3 = repository.getById(ImmutableDataContractKey.builder().name(n3).build());
//        System.out.println(dc3);
    }
}
