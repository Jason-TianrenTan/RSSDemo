package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.RefreshSourceBean;

public class RefreshSourceEvent {

    public enum EventType {
        SINGLE_RSS,//单个RSS源
        BATCH_RSS //全部RSS源
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    private EventType type;


    public RefreshSourceBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(RefreshSourceBean.ResResultBean result) {
        this.result = result;
    }

    private RefreshSourceBean.ResResultBean result;

    public RefreshSourceEvent(RefreshSourceBean.ResResultBean bean, EventType type) {
        result = bean;
        this.type = type;
    }


}
