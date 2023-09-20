package com.thrivent.repository.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;
import java.util.Set;

@DynamoDbBean
public class DataContractCopy {

    String domain;
    String description;



    String name;



    public void setName(String name) {
        this.name = name.split("#")[1];
    }
    public void setSortKey(String latest)
    {}

    @DynamoDbAttribute("partition_key")
    @DynamoDbPartitionKey
    public String getName() {
        return "DC#"+this.name;
    }
    @DynamoDbAttribute("sort_key")
    @DynamoDbSortKey
    public String getSortKey() {
        return "latest";
    }
    @DynamoDbAttribute("domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    @DynamoDbAttribute("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }








}
