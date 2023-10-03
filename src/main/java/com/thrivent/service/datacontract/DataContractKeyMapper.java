package com.thrivent.service.datacontract;

import com.thrivent.repository.dynamodb.AbstractDynamoDbKeyMapper;
import com.thrivent.datacontract.ImmutableDataContract;
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
                .sortValue(sortValue((key.version().isEmpty())?null:key.version().getAsLong()))
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
        System.out.println("primarySortKeyGetter-->>"+entity);
        System.out.println("version in  primarySortKeyGetter-->>"+entity.version());
        return sortValue(entity.version());
    }

    @Override
    public void primarySortKeySetter(ImmutableDataContract.Builder entityBuilder, String value) {
        System.out.println("value-->>"+value);

    }

    private String partitionValue(String name) {
        final String n = Objects.requireNonNull(name);
        return DC + SEPARATOR + n;
    }

    private String sortValue(Long version) {
        System.out.println("version -->>"+version);
        System.out.println((version == null)?LATEST_LABEL:version(version));
        return (version == null)?LATEST_LABEL:version(version);
    }

    private String version(Long version) {
        Validate.isTrue(version > 0, "Version should be greater than 0.");
        return VERSION_PREFIX + version;
    }

    private String extractName(String partitionKey) {
        final String k = Objects.requireNonNull(partitionKey);
        return StringUtils.split(k, SEPARATOR)[1];
    }
}
