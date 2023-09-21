package com.thrivent.datacontract;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

final class DataContractKeyHelper {

    private static final String DC = "DC";
    private static final String SEPARATOR = "#";
    private static final String LATEST_LABEL = "LATEST";
    private static final String VERSION_PREFIX = "v";

    public static String version(int version) {
        Validate.isTrue(version > 0, "Version should be greater than 0.");
        return VERSION_PREFIX + version;
    }

    public static String partitionKey(String name) {
        final String n = Objects.requireNonNull(name);
        return DC + SEPARATOR + n;
    }

    public static String partitionKey(DataContract dataContract) {
        final DataContract dc = Objects.requireNonNull(dataContract);
        return partitionKey(dc.name());
    }

    public static String sortKey(long version) {
        return LATEST_LABEL;
    }

    public static String sortKey(DataContract dataContract) {
        final DataContract dc = Objects.requireNonNull(dataContract);
        return sortKey(0);
    }

    public static String extractName(String partitionKey) {
        final String k = Objects.requireNonNull(partitionKey);
        return StringUtils.split(k, SEPARATOR)[1];
    }

    public static void setterPartitionKey(ImmutableDataContract.Builder builder, String s) {
        builder.name(extractName(s));
    }
}
