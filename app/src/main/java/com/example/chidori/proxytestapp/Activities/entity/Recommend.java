package com.example.chidori.proxytestapp.Activities.entity;

public class Recommend {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String title;
    private String url;

    public Recommend(String title, String url, String date, String source, String desc) {
        this.title = title;
        this.url = url;
        this.date = date;
        this.source = source;
        this.desc = desc;
    }

    private String date;
    private String source;
    private String desc;


}
