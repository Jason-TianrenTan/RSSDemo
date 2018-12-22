package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.AddSourceBean;

public class AddSourceEvent {

    public AddSourceBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(AddSourceBean.ResResultBean result) {
        this.result = result;
    }

    private AddSourceBean.ResResultBean result;

    public AddSourceEvent(AddSourceBean.ResResultBean bean) {
        result = bean;
    }

}
