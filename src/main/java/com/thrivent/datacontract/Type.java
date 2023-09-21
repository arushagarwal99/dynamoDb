package com.thrivent.datacontract;

public enum Type {
    INGESTION("INGESTION"),
    ACTIVATION("ACTIVATION"),
    TRANSFORMATION("TRANSFORMATION");

    String type;
    Type(String type)
    {
        this.type = type;
    }
    public String getType()
    {
        return this.type;
    }
}
