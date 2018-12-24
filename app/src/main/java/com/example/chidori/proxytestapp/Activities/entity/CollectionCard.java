package com.example.chidori.proxytestapp.Activities.entity;

public class CollectionCard{
    private String id;
    private String title;
    private String level;

    public CollectionCard(String id, String title,int level){
        this.id = id;
        this.title = title;
        switch (level){
            case 0: this.level = "私有";
            case 1: this.level = "组内公开";
            case 2: this.level = "全公开";
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLevel() {
        return level;
    }
}
