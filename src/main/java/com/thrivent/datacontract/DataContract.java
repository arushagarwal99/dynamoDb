package org.example.datacontract;

import static org.immutables.value.Value.Immutable;
import static org.immutables.value.Value.Modifiable;

@Immutable
@Modifiable
public abstract class DataContract {

    public abstract String name();

    public abstract String domain();

    public abstract String description();
}
