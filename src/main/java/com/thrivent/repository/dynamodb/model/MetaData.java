package com.thrivent.repository.dynamodb.model;

public class MetaData {
     String partitionKey;


     String sortKey;
     String value;

     public MetaData(String partitionKey, String sortKey, String value) {
          this.partitionKey = partitionKey;
          this.sortKey = sortKey;
          this.value = value;
     }
     public MetaData()
     {

     }


}
