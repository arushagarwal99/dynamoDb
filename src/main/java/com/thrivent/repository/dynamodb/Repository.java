package com.thrivent.repository.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

public interface Repository<E>  {

     void addData(E e);
    TableSchema<E> getTableSchema();
    DynamoDbTable<E> getDynamoDbTable();
     E getDataByPartitionKeyAndSortKey(String portionKey, String SortKey);







}
