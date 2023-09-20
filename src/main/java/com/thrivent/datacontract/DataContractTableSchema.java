package com.thrivent.datacontract;

import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey;
import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primarySortKey;

import com.thrivent.aws.dynamodb.TableSchemaBuilder;
import software.amazon.awssdk.enhanced.dynamodb.mapper.ImmutableAttribute;

class DataContractTableSchema extends TableSchemaBuilder<DataContract, ImmutableDataContract.Builder> {

    DataContractTableSchema() {
        super(DataContract.class, ImmutableDataContract.Builder.class);
    }

    @Override
    public TableSchema<DataContract> build() {
        return builder(ImmutableDataContract::builder, ImmutableDataContract.Builder::build)
                .addAttribute(partitionKey())
                .addAttribute(sortKey())
                .addAttribute(name())
                .addAttribute(domain())
                .addAttribute(description())
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> partitionKey() {
        return attribute(String.class)
                .tags(primaryPartitionKey())
                .name("partitionKey")
                .getter(DataContractKeyHelper::partitionKey)
                .setter(NOOP)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> sortKey() {
        return attribute(String.class)
                .tags(primarySortKey())
                .name("sortKey")
                .getter(DataContractKeyHelper::sortKey)
                .setter(NOOP)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> name() {
        return attribute(String.class)
                .name("name")
                .getter(DataContract::name)
                .setter(ImmutableDataContract.Builder::name)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> domain() {
        return attribute(String.class)
                .name("domain")
                .getter(DataContract::domain)
                .setter(ImmutableDataContract.Builder::domain)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> description() {
        return attribute(String.class)
                .name("description")
                .getter(DataContract::description)
                .setter(ImmutableDataContract.Builder::description)
                .build();
    }
}
