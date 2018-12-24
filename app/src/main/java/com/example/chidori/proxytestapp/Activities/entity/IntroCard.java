package com.example.chidori.proxytestapp.Activities.entity;

public class IntroCard{
    private String title;
    private String detail;

    public String getSourceId() {
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

    public IntroCard(String sourceId,String title,String detail, String updateTime){
        this.sourceId = sourceId;
        this.title = title;
        this.detail = detail;
        this.updateTime = updateTime;
    }

    public String getDetail() {
        return detail;
    }

    public String getTitle() {
        return title;
    }

}
