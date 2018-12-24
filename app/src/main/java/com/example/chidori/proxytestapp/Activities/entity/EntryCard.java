package com.example.chidori.proxytestapp.Activities.entity;

public class EntryCard {
    private String title;
    private String detail;
    public String getId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    private String sourceId;
    private String updateTime;

    public EntryCard(String id, String title, String detail){
        this.sourceId = id;
        this.title = title;
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public String getTitle() {
        return title;
    }

}
