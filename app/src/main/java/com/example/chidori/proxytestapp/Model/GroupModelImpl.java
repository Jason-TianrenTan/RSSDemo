package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.UserGroupsBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class GroupModelImpl implements Contract.IGroupModel {

    private ArrayList<Group> userGroups;

    public void setupUserGroups(UserGroupsBean.ResResultBean bean) {
        userGroups = new ArrayList<>();
        ArrayList<UserGroupsBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(bean.getCurData());
        for (UserGroupsBean.ResResultBean.CurDataBean curData : curDatas) {
            userGroups.add(new Group(curData.getGroupId(), curData.getName(), curData.getGroupNum(),
                    curData.getDescription(), curData.getUserId(), curData.getCreateTime(), curData.getUpdateTime()));
        }
    }

    public ArrayList<Group> getUserGroups() {
        return userGroups;
    }

    @Override
    public void doCreateGroup(String groupName, String desc) {
        new UniversalPresenter().CreateGroup(groupName, desc, Config.userId);
    }

    @Override
    public void doGetUserGroups() {
        new UniversalPresenter().GetUserGroups(Config.userId);
    }
}
