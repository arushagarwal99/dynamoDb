package com.thrivent.service.datacontract;

import com.thrivent.repository.dynamodb.TableSchemaBuilder;

import com.thrivent.datacontract.ImmutableTaskData;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.ImmutableAttribute;

import java.util.List;
import java.util.Map;



public class TaskDataTableSchema extends TableSchemaBuilder<TaskData, ImmutableTaskData.Builder> {

    public TaskDataTableSchema() {
        super(TaskData.class, ImmutableTaskData.Builder.class);
    }

    @Override
    public String tableName() {
        return null;
    }

    @Override
    public TableSchema<TaskData> build() {
        return builder(ImmutableTaskData::builder, ImmutableTaskData.Builder::build)
                .addAttribute(targetDataSetName())
                .addAttribute(sourceName())
                .addAttribute(sourceType())
                .addAttribute(sourceDataSetName())
                .addAttribute(taskName())
                .addAttribute(taskType())
                .addAttribute(dependsOn())
                .addAttribute(sourceDataSetOption())
                .addAttribute(targetDataSetOption())
                .addAttribute(taskOptions())
                .build();
    }
    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, String> targetDataSetName() {
        return attribute(String.class)
                .name("targetDataSetNameTask")
                .getter(TaskData::targetDataSetName)
                .setter(ImmutableTaskData.Builder::targetDataSetName)
                .build();
    }
    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, String> sourceName() {
        return attribute(String.class)
                .name("sourceName")
                .getter(TaskData::sourceName)
                .setter(ImmutableTaskData.Builder::sourceName)
                .build();
    }

    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, String> sourceType() {
        return attribute(String.class)
                .name("sourceType")
                .getter(TaskData::sourceType)
                .setter(ImmutableTaskData.Builder::sourceType)
                .build();
    }
    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, String> sourceDataSetName() {
        return attribute(String.class)
                .name("sourceDataSetName")
                .getter(TaskData::sourceDataSetName)
                .setter(ImmutableTaskData.Builder::sourceDataSetName)
                .build();
    }
    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, String> taskName() {
        return attribute(String.class)
                .name("taskName")
                .getter(TaskData::taskName)
                .setter(ImmutableTaskData.Builder::taskName)
                .build();
    }

    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, TaskData.TaskType> taskType() {
        return attribute(TaskData.TaskType.class)
                .name("taskType")
                .getter(TaskData::taskType)
                .setter(ImmutableTaskData.Builder::taskType)
                .build();
    }

    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, List<String>> dependsOn() {
        return attribute(EnhancedType.listOf(String.class))
                .name("dependsOn")
                .getter(TaskData::dependsOn)
                .setter(ImmutableTaskData.Builder::dependsOn)
                .build();
    }
    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, Map<String, String>> sourceDataSetOption() {
        return attribute( EnhancedType.mapOf(String.class, String.class))
                .name("sourceDataSetOption")
                .getter(TaskData::sourceDataSetOption)
                .setter(ImmutableTaskData.Builder::sourceDataSetOption)
                .build();
    }
    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, Map<String, String>> targetDataSetOption() {
        return attribute( EnhancedType.mapOf(String.class, String.class))
                .name("targetDataSetOption")
                .getter(TaskData::targetDataSetOption)
                .setter(ImmutableTaskData.Builder::targetDataSetOption)
                .build();
    }
    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, Map<String, String>> taskOptions() {
        return attribute( EnhancedType.mapOf(String.class, String.class))
                .name("taskOptions")
                .getter(TaskData::taskOptions)
                .setter(ImmutableTaskData.Builder::taskOptions)
                .build();
    }

}
