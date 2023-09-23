package com.thrivent.datacontract;

import com.thrivent.aws.dynamodb.TableSchemaBuilder;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.extensions.AutoGeneratedTimestampRecordExtension;
import software.amazon.awssdk.enhanced.dynamodb.mapper.ImmutableAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags;
import software.amazon.awssdk.enhanced.dynamodb.mapper.UpdateBehavior;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Set;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey;
import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primarySortKey;

class DataContractTableSchema extends TableSchemaBuilder<DataContract, ImmutableDataContract.Builder> {

    private static final String TABLE_NAME = "tdp-datacontract-sandbox";

    private final DataContractKeyMapper keyMapper;

    @Inject
    DataContractTableSchema(DataContractKeyMapper keyMapper) {
        super(DataContract.class, ImmutableDataContract.Builder.class);
        this.keyMapper = keyMapper;
    }

    @Override
    public String tableName() {
        return TABLE_NAME;
    }

    @Override
    public TableSchema<DataContract> build() {
        return builder(ImmutableDataContract::builder, ImmutableDataContract.Builder::build)
                .addAttribute(partitionKey())
                .addAttribute(sortKey())
                .addAttribute(domain())
                .addAttribute(description())
                .addAttribute(protocolVersion())
                .addAttribute(type())
                .addAttribute(owner())
                .addAttribute(schedule())
                .addAttribute(retentionPeriod())
                .addAttribute(emailNotifications())
                .addAttribute(createdAt())
                .addAttribute(updatedAt())
                .addAttribute(deleted())
                .addAttribute(deletedAt())
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> partitionKey() {
        return attribute(String.class)
                .tags(primaryPartitionKey())
                .name("partitionKey")
                .getter(keyMapper::primaryPartitionKeyGetter)
                .setter(keyMapper::primaryPartitionKeySetter)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> sortKey() {
        return attribute(String.class)
                .tags(primarySortKey())
                .name("sortKey")
                .getter(keyMapper::primarySortKeyGetter)
                .setter(keyMapper::primarySortKeySetter)
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

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> protocolVersion() {
        return attribute(String.class)
                .name("protocolVersion")
                .getter(DataContract::protocolVersion)
                .setter(ImmutableDataContract.Builder::protocolVersion)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, DataContract.Type> type() {
        return attribute(DataContract.Type.class)
                .name("type")
                .getter(DataContract::type)
                .setter(ImmutableDataContract.Builder::type)
                .build();
    }


    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> owner() {
        return attribute(String.class)
                .name("owner")
                .getter(DataContract::owner)
                .setter(ImmutableDataContract.Builder::owner)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, String> schedule() {
        return attribute(String.class)
                .name("schedule")
                .getter(DataContract::schedule)
                .setter(ImmutableDataContract.Builder::schedule)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, Integer> retentionPeriod() {
        return attribute(Integer.class)
                .name("retentionPeriod")
                .getter(DataContract::retentionPeriod)
                .setter(ImmutableDataContract.Builder::retentionPeriod)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, Set<String>> emailNotifications() {
        return attribute(EnhancedType.setOf(String.class))
                .name("emailNotifications")
                .getter(DataContract::emailNotifications)
                .setter(ImmutableDataContract.Builder::emailNotifications)
                .build();

    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, Instant> createdAt() {
        return attribute(Instant.class)
                .name("createdAt")
                .getter(DataContract::createdAt)
                .setter(ImmutableDataContract.Builder::createdAt)
                .addTag(AutoGeneratedTimestampRecordExtension.AttributeTags.autoGeneratedTimestampAttribute())
                .addTag(StaticAttributeTags.updateBehavior(UpdateBehavior.WRITE_IF_NOT_EXISTS))
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, Instant> updatedAt() {
        return attribute(Instant.class)
                .name("updatedAt")
                .getter(DataContract::updatedAt)
                .setter(ImmutableDataContract.Builder::updatedAt)
                .addTag(AutoGeneratedTimestampRecordExtension.AttributeTags.autoGeneratedTimestampAttribute())
                .addTag(StaticAttributeTags.updateBehavior(UpdateBehavior.WRITE_ALWAYS))
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, Boolean> deleted() {
        return attribute(Boolean.class)
                .name("deleted")
                .getter(DataContract::deleted)
                .setter(ImmutableDataContract.Builder::deleted)
                .build();
    }

    private ImmutableAttribute<DataContract, ImmutableDataContract.Builder, Instant> deletedAt() {
        return attribute(Instant.class)
                .name("deletedAt")
                .getter(DataContract::deletedAt)
                .setter(ImmutableDataContract.Builder::deletedAt)
                .build();
    }
}
