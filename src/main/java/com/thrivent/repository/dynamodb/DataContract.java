package com.thrivent.repository.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;
import java.util.Set;

@DynamoDbBean
public class DataContract {
    int latest;
    String protocolVersion;
    Type type;
    String domain;
    String description;
    String owner;
    int retentionPeriod;
    Set<String> emailNotifications;
    String schedule;
    String createdAt;
    String updatedAt;
    boolean deleted;
    String deletedAt;
    String taskMap;

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    List<Task> task;
    @DynamoDbAttribute("partition_key")
    @DynamoDbPartitionKey
    public String getPartitionKey() {
        return "";
    }
    @DynamoDbAttribute("sort_key")
    @DynamoDbSortKey
    public String getSortKey() {
        return "";
    }
    @DynamoDbAttribute( "latest")
    public int getLatest() {
        return latest;
    }

    public void setLatest(int latest) {
        this.latest = latest;
    }
    @DynamoDbAttribute("protocolVersion")
    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Type getType() {
        return type;
    }
    @DynamoDbAttribute("type")
    public String getTypeForDynamoDb(){
        return "";
    }

    public void setType(Type type) {
        this.type = type;
    }
    @DynamoDbAttribute("domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    @DynamoDbAttribute("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @DynamoDbAttribute("owner")
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    @DynamoDbAttribute("retentionPeriod")
    public int getRetentionPeriod() {
        return retentionPeriod;
    }

    public void setRetentionPeriod(int retentionPeriod) {
        this.retentionPeriod = retentionPeriod;
    }

    public Set<String> getEmailNotifications() {
        return emailNotifications;
    }
    @DynamoDbAttribute("emailNotifications")
    public String getEmailNotificationsForDynamoDb()
    {
        return "";
    }

    public void setEmailNotifications(Set<String> emailNotifications) {
        this.emailNotifications = emailNotifications;
    }
    @DynamoDbAttribute("schedule")
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    @DynamoDbAttribute("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    @DynamoDbAttribute("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    @DynamoDbAttribute("deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    @DynamoDbAttribute("deletedAt")
    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }
    @DynamoDbAttribute("taskMap")
    public String getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(String taskMap) {
        this.taskMap = taskMap;
    }
}
