package com.example.chidori.proxytestapp.Activities.entity;

public class Source {
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    String sourceId;
    private String name;
    private String description;
    private String link;
    private int type;
    private String createTime;
    private String updateTime;


    public Source(String sourceId, String name, String description,
                  String link, int type,
                  String createTime, String updateTime) {
        this.sourceId = sourceId;
        this.name = name;
        this.description = description;
        this.link = link;
        this.type = type;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
