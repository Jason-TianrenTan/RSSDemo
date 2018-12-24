package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.GroupModifyBean;

public class GroupModifyEvent {

    public enum EventType {
        ENTER, QUIT
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    private EventType type;
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private boolean success;

    public GroupModifyEvent(boolean flag, EventType type,GroupModifyBean.ResResultBean bean) {
        this.success = flag;
        this.type = type;
        this.result = bean;
    }

    public GroupModifyBean.ResResultBean getResult() {
        return result;
    }
    private GroupModifyBean.ResResultBean result;

    public void setResult(GroupModifyBean.ResResultBean result) {
        this.result = result;
    }

}
