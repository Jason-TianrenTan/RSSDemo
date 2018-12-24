package com.example.chidori.proxytestapp.Events;

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

    public GroupModifyEvent(boolean flag, EventType type) {
        this.success = flag;
    }

}
