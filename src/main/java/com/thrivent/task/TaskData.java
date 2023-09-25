package com.thrivent.task;

import com.thrivent.core.annotations.ImmutablesSettings;
import com.thrivent.core.annotations.Nullable;
import org.immutables.value.Value;

@Value.Immutable
@ImmutablesSettings
public abstract class TaskData {
 public abstract  String sourceName();
 public abstract  String sourceType();
 public abstract  String sourceDataSetName();
 public abstract  String targetDataSetName();
 public abstract  String taskName();
 public abstract  String taskType();
 public abstract  String dependsOn();
 public abstract SourceDataSetOption sourceDataSetOption();


}
