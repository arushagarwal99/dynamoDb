package com.thrivent.aws.dynamodb;

import com.amazonaws.services.dynamodbv2.local.main.ServerRunner;
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer;
import org.apache.commons.cli.ParseException;

public final class DynamoDbLocal {

    private final DynamoDBProxyServer server;

    private DynamoDbLocal(DynamoDBProxyServer server) {
        this.server = server;
    }

    static DynamoDbLocal create(int port) {
        try {
            final DynamoDBProxyServer server =
                    ServerRunner.createServerFromCommandLineArgs(
                            new String[] {"-inMemory", "-port", Integer.toString(port)});
            return new DynamoDbLocal(server);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final void start() {
        try {
            this.server.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public final void stop() {
        try {
            this.server.stop();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
