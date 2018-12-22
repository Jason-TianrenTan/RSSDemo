package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.SaveRSSBean;

public class SaveRSSEvent {

    public SaveRSSBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(SaveRSSBean.ResResultBean result) {
        this.result = result;
    }

    private SaveRSSBean.ResResultBean result;

    public SaveRSSEvent(SaveRSSBean.ResResultBean bean) {
        result = bean;
    }


}
