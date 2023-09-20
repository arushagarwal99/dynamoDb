package org.example.datacontract;

import org.apache.commons.lang3.Validate;

import java.util.OptionalLong;

import static org.immutables.value.Value.Check;
import static org.immutables.value.Value.Immutable;

@Immutable
public abstract class DataContractKey {

    public abstract String name();

    public abstract OptionalLong version();

    @Check
    protected void check() {
        Validate.isTrue(version().isEmpty() || version().getAsLong() > 0, "Version should be greater than 0.");
    }
}
