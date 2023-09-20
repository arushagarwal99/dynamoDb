package org.example.datacontract;

import org.example.aws.dynamodb.KeyMapper;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.Objects;

public class DataContractKeyMapper implements KeyMapper<DataContractKey> {

    @Override
    public Key map(DataContractKey key) {
        Objects.requireNonNull(key);
        return Key.builder()
                .partitionValue(DataContractKeyHelper.partitionKey(key.name()))
                .sortValue(DataContractKeyHelper.sortKey(0))
                .build();
    }
}
