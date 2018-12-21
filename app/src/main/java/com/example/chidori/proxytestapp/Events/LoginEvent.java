package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;

public class LoginEvent {

    public LoginBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(LoginBean.ResResultBean result) {
        this.result = result;
    }

    private LoginBean.ResResultBean result;

    public LoginEvent(LoginBean.ResResultBean bean) {
        result = bean;
    }
}
