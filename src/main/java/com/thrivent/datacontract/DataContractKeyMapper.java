package com.thrivent.datacontract;

import com.thrivent.aws.dynamodb.AbstractDynamoDbKeyMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import javax.inject.Inject;
import java.util.Objects;

public class DataContractKeyMapper extends AbstractDynamoDbKeyMapper<DataContract, ImmutableDataContract.Builder, DataContractKey> {

    private static final String DC = "DC";
    private static final String SEPARATOR = "#";
    private static final String LATEST_LABEL = "LATEST";
    private static final String VERSION_PREFIX = "v";

    @Inject
    public DataContractKeyMapper() {

    }

    @Override
    public Key map(DataContractKey key) {
        Objects.requireNonNull(key);
        return Key.builder()
                .partitionValue(partitionValue(key.name()))
                .sortValue(sortValue(0))
                .build();
    }

    @Override
    public String primaryPartitionKeyGetter(DataContract entity) {
        final DataContract dc = Objects.requireNonNull(entity);
        return partitionValue(dc.name());
    }

    @Override
    public void primaryPartitionKeySetter(ImmutableDataContract.Builder entityBuilder, String value) {
        entityBuilder.name(extractName(value));
    }

    @Override
    public String primarySortKeyGetter(DataContract entity) {
        final DataContract dc = Objects.requireNonNull(entity);
        return sortValue(0);
    }

    @Override
    public void primarySortKeySetter(ImmutableDataContract.Builder entityBuilder, String value) {

    }

    private String partitionValue(String name) {
        final String n = Objects.requireNonNull(name);
        return DC + SEPARATOR + n;
    }

    private String sortValue(long version) {
        return LATEST_LABEL;
    }

    private String version(int version) {
        Validate.isTrue(version > 0, "Version should be greater than 0.");
        return VERSION_PREFIX + version;
    }

    private String extractName(String partitionKey) {
        final String k = Objects.requireNonNull(partitionKey);
        return StringUtils.split(k, SEPARATOR)[1];
    }
}
