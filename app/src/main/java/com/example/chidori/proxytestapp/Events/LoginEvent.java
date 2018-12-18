package com.example.chidori.proxytestapp.Events;

public class LoginEvent {

    private String result;
    public LoginEvent(String str) {
        this.result = str;
    }

    public String getResult() {
        return result;
    }
}
