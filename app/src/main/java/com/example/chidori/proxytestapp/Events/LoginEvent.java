package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;

public class LoginEvent {

    public LoginBean.ResResultBean getResult() {
        return result;
    }

    private LoginBean.ResResultBean result;
    public LoginEvent(LoginBean.ResResultBean bean) {
        this.result = bean;
    }

}
