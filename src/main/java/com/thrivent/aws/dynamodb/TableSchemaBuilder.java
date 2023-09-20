package org.example.aws.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.mapper.ImmutableAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.StaticImmutableTableSchema;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class TableSchemaBuilder<T, B> {

    protected final BiConsumer<B, String> NOOP = (t, u) -> {
    };

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

    public abstract TableSchema<T> build();
}
