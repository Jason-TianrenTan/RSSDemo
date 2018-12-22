package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.CollectionListBean;

public class CollectionListEvent {

    public enum EventType {
        COLLECTION_LIST,//指定publicStatus状态的Collection
        ALL_PUBLIC,
        GROUP_PUBLIC
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    private EventType type;
    public CollectionListBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(CollectionListBean.ResResultBean result) {
        this.result = result;
    }

    private CollectionListBean.ResResultBean result;

    public CollectionListEvent(CollectionListBean.ResResultBean bean, EventType type) {
        result = bean;
        this.type = type;
    }


}
