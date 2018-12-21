package com.example.chidori.proxytestapp.Activities.entity;

public class IntroCard{
    private String id;
    private String title;
    private String detail;

    public IntroCard(String id,String title,String detail){
        this.id = id;
        this.title = title;
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
