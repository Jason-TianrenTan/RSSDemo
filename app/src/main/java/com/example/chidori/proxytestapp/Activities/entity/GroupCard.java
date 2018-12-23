package com.example.chidori.proxytestapp.Activities.entity;

public class GroupCard {
    private String id;
    private String title;

    public GroupCard(String id,String name) {
        this.id = id;
        this.title = name;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

