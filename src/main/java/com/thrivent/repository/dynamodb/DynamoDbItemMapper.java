package com.thrivent.repository.dynamodb;

import com.thrivent.repository.dynamodb.model.MetaData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface DynamoDbItemMapper {
    DynamoDbItemMapper INSTANCE = Mappers.getMapper(DynamoDbItemMapper.class);

    MetaData mapFromItem(MetadataTableItem metadataTableItem);
    @Mapping(target = "partitionKey", ignore = true)
    @Mapping(target = "sortKey", ignore = true)
    @Mapping(target = "value", ignore = true)
    MetadataTableItem mapToItem(MetaData metaData);
}
