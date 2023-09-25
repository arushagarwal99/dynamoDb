package com.thrivent.datacontract;

import com.thrivent.core.annotations.ImmutablesSettings;
import com.thrivent.core.annotations.Nullable;
import org.immutables.value.Value;

import java.util.List;
import java.util.Map;

@Value.Immutable
@ImmutablesSettings
public abstract class TaskData {
 public abstract  String sourceName();
 public abstract  String sourceType();
 public abstract  String sourceDataSetName();
 public abstract  String targetDataSetName();
 public abstract  String taskName();
 public abstract  TaskType taskType();
 public abstract List<String> dependsOn();
 public abstract Map<String, String> sourceDataSetOption();
 public abstract Map<String, String> targetDataSetOption();
 public abstract Map<String, String> taskOptions();

 public enum TaskType {
  KAFKA_INJECTION;
 }


}

