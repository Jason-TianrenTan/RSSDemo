package com.example.chidori.proxytestapp.Activities.entity;

public class UserCard{
    private String id;
    private String title;

    public UserCard(String id,String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
