package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.CreateGroupEvent;
import com.example.chidori.proxytestapp.Events.UserGroupsEvent;
import com.example.chidori.proxytestapp.Model.GroupModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class GroupPresenterImpl implements Contract.IGroupPresenter {

    private GroupModelImpl model;

    private Contract.IGroupView groupView;

    public GroupPresenterImpl() {
        model = new GroupModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IGroupView view) {
        this.groupView = view;
    }

    public ArrayList<Group> getUserGroups() { return model.getUserGroups(); }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserGroupsRetrieved(UserGroupsEvent userGroupsEvent) {
        if (userGroupsEvent.getResult().isIsSuccess()) {
            model.setupUserGroups(userGroupsEvent.getResult());
            groupView.onUserGroupsRetrieved("success");
        }
        else groupView.onUserGroupsRetrieved("failure");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGroupCreated(CreateGroupEvent createGroupEvent) {
        if (createGroupEvent.getResult().isIsSuccess())
            groupView.onGroupCreated("success");
        else groupView.onGroupCreated("failure");
    }

    @Override
    public void doCreateGroup(String groupName, String desc) {
        model.doCreateGroup(groupName, desc);
    }

    @Override
    public void doGetUserGroups() {
        model.doGetUserGroups();
    }

}
