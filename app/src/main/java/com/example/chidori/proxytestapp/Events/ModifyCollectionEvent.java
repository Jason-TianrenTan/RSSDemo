package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.ModifyCollectionBean;

public class ModifyCollectionEvent {

    public enum EventType {
        CHANGE_STATUS, //修改公开状态
        CLEAR, //清除收藏夹
        ADD_ENTRY, //添加文章
        MOVE_ENTRY,//移动文章
        DELETE//删除收藏夹
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    private EventType type;
    public ModifyCollectionBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(ModifyCollectionBean.ResResultBean result) {
        this.result = result;
    }

    private ModifyCollectionBean.ResResultBean result;

    public ModifyCollectionEvent(ModifyCollectionBean.ResResultBean bean, EventType type) {
        result = bean;
        this.type = type;
    }
}
