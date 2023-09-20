package com.thrivent.repository.dynamodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public interface Repository<E>  {

     void addData(E e);
    TableSchema<E> getTableSchema();
    DynamoDbTable<E> getDynamoDbTable();
     E getDataByPartitionKeyAndSortKey(String portionKey, String SortKey);







}
