package com.thrivent.task;

import com.thrivent.aws.dynamodb.TableSchemaBuilder;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.ImmutableAttribute;

public class SourceDataSetOptionSchema extends TableSchemaBuilder<SourceDataSetOption, ImmutableSourceDataSetOption.Builder> {

    public SourceDataSetOptionSchema() {
        super(SourceDataSetOption.class, ImmutableSourceDataSetOption.Builder.class);
    }

    @Override
    public String tableName() {
        return null;
    }

    @Override
    public TableSchema<SourceDataSetOption> build() {
        return builder(ImmutableSourceDataSetOption::builder, ImmutableSourceDataSetOption.Builder::build)
                .addAttribute(topicName())


                .build();
    }
    private ImmutableAttribute<SourceDataSetOption, ImmutableSourceDataSetOption.Builder, String> topicName() {
        return attribute(String.class)
                .name("topicName")
                .getter(SourceDataSetOption::topicName)
                .setter(ImmutableSourceDataSetOption.Builder::topicName)
                .build();
    }

}