package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;

public class EntryListEvent {

    public enum EventType {
        PUBLIC_TO_ALL,
        PUBLIC_TO_GROUP,
        LIST_BY_SOURCE,
        LIST_COLLECTION
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    private EventType type;
    public EntryListBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(EntryListBean.ResResultBean result) {
        this.result = result;
    }

    private EntryListBean.ResResultBean result;

    public EntryListEvent(EntryListBean.ResResultBean bean, EventType type) {
        result = bean;
        this.type = type;
    }
}
