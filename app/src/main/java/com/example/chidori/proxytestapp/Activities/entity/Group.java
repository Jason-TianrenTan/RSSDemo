package com.example.chidori.proxytestapp.Activities.entity;

public class Group {
    private String groupId;
    private String name;
    private String groupNum;
    private String description;
    private String creatorId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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

    private String createTime;

    public Group(String groupId, String name, String groupNum, String description, String creatorId, String createTime, String updateTime) {
        this.groupId = groupId;
        this.name = name;
        this.groupNum = groupNum;
        this.description = description;
        this.creatorId = creatorId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    private String updateTime;

}
