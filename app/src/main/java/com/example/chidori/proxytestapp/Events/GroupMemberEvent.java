package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.GroupMemberBean;

public class GroupMemberEvent {

    public GroupMemberBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(GroupMemberBean.ResResultBean result) {
        this.result = result;
    }

    private GroupMemberBean.ResResultBean result;

    public GroupMemberEvent(GroupMemberBean.ResResultBean bean) {
        result = bean;
    }


}
