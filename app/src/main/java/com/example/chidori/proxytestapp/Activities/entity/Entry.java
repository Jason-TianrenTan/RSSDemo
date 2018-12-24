package com.example.chidori.proxytestapp.Activities.entity;

public class Entry {
    private String entryId;
    private String title;
    private String description;
    private String entryLink;
    private String collectionId;

    public Entry(String entryId, String title, String description, String entryLink, String collectionId, String sourceId, String sourceName, String sourceLink, String collectionName, String updateTime) {
        this.entryId = entryId;
        this.title = title;
        this.description = description;
        this.entryLink = entryLink;
        this.collectionId = collectionId;
        this.sourceId = sourceId;
        this.sourceName = sourceName;
        this.sourceLink = sourceLink;
        this.collectionName = collectionName;
        this.updateTime = updateTime;
    }

    private String sourceId;

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntryLink() {
        return entryLink;
    }

    public void setEntryLink(String entryLink) {
        this.entryLink = entryLink;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    private String sourceName;
    private String sourceLink;
    private String collectionName;
    private String updateTime;
}
