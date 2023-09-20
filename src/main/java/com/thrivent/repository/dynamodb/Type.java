package com.thrivent.repository.dynamodb;

public enum Type {
    INGESTION("INGESTION"),
    ACTIVATION("ACTIVATION"),
    TRANSFORMATION("TRANSFORMATION");

    String type;
    Type(String type)
    {
        this.type = type;
    }
}
