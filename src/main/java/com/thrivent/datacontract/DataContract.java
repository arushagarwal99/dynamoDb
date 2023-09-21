package com.thrivent.datacontract;

import java.util.Set;

import static org.immutables.value.Value.Immutable;
import static org.immutables.value.Value.Modifiable;

@Immutable
@Modifiable
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

    public abstract String createdAt();

    public abstract String updatedAt();

    public abstract boolean deleted();

    public abstract String deletedAt();
}
