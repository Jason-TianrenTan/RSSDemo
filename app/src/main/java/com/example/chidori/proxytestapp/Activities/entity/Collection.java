package com.example.chidori.proxytestapp.Activities.entity;

public class Collection {
    private String collectionId;
    private String name;
    private String description;

    public Collection(String id, String name, String desc, int pbStatus, String create, String update) {
        this.setCollectionId(id);
        this.setName(name);
        this.setDescription(desc);
        this.setPublicStatus(pbStatus);
        this.setCreateTime(create);
        this.setUpdateTime(update);
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
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

    public int getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(int publicStatus) {
        this.publicStatus = publicStatus;
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

    private int publicStatus;
    private String createTime;
    private String updateTime;
}
