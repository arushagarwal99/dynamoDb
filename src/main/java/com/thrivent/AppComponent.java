package com.thrivent;

import com.thrivent.aws.dynamodb.DynamoDbModule;
import com.thrivent.datacontract.DataContractDynamoDbRepository;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { DynamoDbModule.class })
public interface AppComponent {

    DataContractDynamoDbRepository dataContractDynamoDbRepository();
}
