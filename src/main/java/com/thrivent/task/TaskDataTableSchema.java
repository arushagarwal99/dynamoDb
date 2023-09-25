package com.thrivent.task;

import com.thrivent.aws.dynamodb.TableSchemaBuilder;

import com.thrivent.datacontract.DataContract;
import com.thrivent.datacontract.ImmutableDataContract;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.ImmutableAttribute;

import java.util.List;

import static software.amazon.awssdk.enhanced.dynamodb.mapper.StaticAttributeTags.primaryPartitionKey;

public class TaskDataTableSchema extends TableSchemaBuilder<TaskData, ImmutableTaskData.Builder> {
    private  final TableSchema<SourceDataSetOption> GENERIC_RECORD_SCHEMA = new SourceDataSetOptionSchema().build();
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
    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, String> taskType() {
        return attribute(String.class)
                .name("taskType")
                .getter(TaskData::taskType)
                .setter(ImmutableTaskData.Builder::taskType)
                .build();
    }

    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, String> dependsOn() {
        return attribute(String.class)
                .name("dependsOn")
                .getter(TaskData::dependsOn)
                .setter(ImmutableTaskData.Builder::dependsOn)
                .build();
    }
    private ImmutableAttribute<TaskData, ImmutableTaskData.Builder, SourceDataSetOption> sourceDataSetOption() {
        return attribute( EnhancedType.documentOf( SourceDataSetOption.class, GENERIC_RECORD_SCHEMA))
                .name("sourceDataSetOption")
                .getter(TaskData::sourceDataSetOption)
                .setter(ImmutableTaskData.Builder::sourceDataSetOption)
                .build();
    }
}
