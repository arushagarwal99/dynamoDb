package com.thrivent.datacontract;

import com.thrivent.core.annotations.ImmutablesSettings;
import com.thrivent.core.annotations.Nullable;

import org.immutables.value.Value.Default;
import org.immutables.value.Value.Immutable;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Immutable
@ImmutablesSettings
public abstract class DataContract {

    public abstract String name();

    public abstract String domain();

    public abstract String description();

    public abstract String protocolVersion();

    public abstract Type type();

    public abstract String owner();

    public abstract String schedule();

    public abstract int retentionPeriod();

    public abstract Set<String> emailNotifications();
    public abstract List<TaskData> tasks();

    @Nullable
    public abstract Instant createdAt();

    @Nullable
    public abstract Instant updatedAt();

    @Default
    public boolean deleted() {
        return false;
    }

    @Nullable
    public abstract Instant deletedAt();

    public enum Type {
        INGESTION,
        ACTIVATION,
        TRANSFORMATION;
    }
}
