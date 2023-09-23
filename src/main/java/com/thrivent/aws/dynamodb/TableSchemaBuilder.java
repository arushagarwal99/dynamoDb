package com.thrivent.aws.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.ImmutableAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticImmutableTableSchema;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class TableSchemaBuilder<T, B> {

    private final Class<T> immutableItemClass;

    private final Class<B> immutableBuilderClass;

    private final StaticImmutableTableSchema.Builder<T, B> builder;

    protected TableSchemaBuilder(Class<T> immutableItemClass, Class<B> immutableBuilderClass) {
        this.immutableItemClass = immutableItemClass;
        this.immutableBuilderClass = immutableBuilderClass;
        this.builder = TableSchema.builder(immutableItemClass, immutableBuilderClass);
    }

    protected final StaticImmutableTableSchema.Builder<T, B> builder(Supplier<B> newBuilderMethod, Function<B, T> buildMethod) {
        return builder.newItemBuilder(newBuilderMethod, buildMethod);
    }

    protected final <R> ImmutableAttribute.Builder<T, B, R> attribute(Class<R> attributeClass) {
        return ImmutableAttribute.builder(immutableItemClass, immutableBuilderClass, attributeClass);
    }
    protected final <R> ImmutableAttribute.Builder<T, B, R> attribute(EnhancedType<R> attributeType) {
        return ImmutableAttribute.builder(immutableItemClass, immutableBuilderClass, attributeType);
    }

    public abstract String tableName();

    public abstract TableSchema<T> build();
}
