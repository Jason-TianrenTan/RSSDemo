package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.UserDetailBean;

public class UserDetailEvent {

    public UserDetailBean.ResResultBean getResult() {
        return result;
    }

    private UserDetailBean.ResResultBean result;

    public UserDetailEvent(UserDetailBean.ResResultBean bean) {
        result = bean;
    }


}
