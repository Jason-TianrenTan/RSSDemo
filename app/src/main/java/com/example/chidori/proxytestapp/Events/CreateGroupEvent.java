package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.CreateGroupBean;

public class CreateGroupEvent {

    public CreateGroupBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(CreateGroupBean.ResResultBean result) {
        this.result = result;
    }

    private CreateGroupBean.ResResultBean result;

    public CreateGroupEvent(CreateGroupBean.ResResultBean bean) {
        result = bean;
    }
}
