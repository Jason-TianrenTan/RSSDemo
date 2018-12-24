package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.UserGroupsBean;

public class UserGroupsEvent {

    public UserGroupsBean.ResResultBean getResult() {
        return result;
    }

    private UserGroupsBean.ResResultBean result;

    public UserGroupsEvent(UserGroupsBean.ResResultBean bean) {
        result = bean;
    }


}
