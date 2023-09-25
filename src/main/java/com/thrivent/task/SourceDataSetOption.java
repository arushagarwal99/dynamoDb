package com.thrivent.task;

import com.thrivent.core.annotations.ImmutablesSettings;
import org.immutables.value.Value;

@Value.Immutable
@ImmutablesSettings
public abstract class SourceDataSetOption {
    public abstract  String topicName();
    public abstract  String startingOffSet();
    public abstract  String format();



}
