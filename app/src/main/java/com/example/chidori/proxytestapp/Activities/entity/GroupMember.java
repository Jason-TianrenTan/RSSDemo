package com.example.chidori.proxytestapp.Activities.entity;

public class GroupMember {

    private String userId;
    private String username;
    private int sex;
    private String phone;

    public GroupMember(String userId, String username, int sex, String phone, String email, Object avatar) {
        this.userId = userId;
        this.username = username;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
    }

    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }

    private Object avatar;
}
