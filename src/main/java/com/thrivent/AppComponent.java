package com.thrivent;

import com.thrivent.repository.dynamodb.DynamoDbModule;
import com.thrivent.service.datacontract.DataContractDynamoDbRepository;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { DynamoDbModule.class })
public interface AppComponent {

    DataContractDynamoDbRepository dataContractDynamoDbRepository();
}
