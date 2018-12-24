package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.GroupMember;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.GroupMemberEvent;
import com.example.chidori.proxytestapp.Events.UserDetailEvent;
import com.example.chidori.proxytestapp.Model.TabModelImpl;
import com.example.chidori.proxytestapp.Utils.Beans.GroupMemberBean;
import com.example.chidori.proxytestapp.Utils.Beans.UserDetailBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class TabModelPresenterImpl implements Contract.ITabPresenter {

    private TabModelImpl model;

    private Contract.ITabView tabView;

    public TabModelPresenterImpl() {
        model = new TabModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.ITabView view) {
        tabView = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGroupMemberResult(GroupMemberEvent groupMemberEvent) {
        if (groupMemberEvent.getResult().isIsSuccess()) {
            model.setupMembers(groupMemberEvent.getResult());
            tabView.onGroupMembersResult("success");
        } else tabView.onGroupMembersResult("failure");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserDetailRetrieved(UserDetailEvent userDetailEvent) {
        GroupMember userInfo = null;
        if (userDetailEvent.getResult().isIsSuccess()) {
            UserDetailBean.ResResultBean.CurDataBean curData = userDetailEvent.getResult().getCurData();
            userInfo = new GroupMember(curData.getUserId(), curData.getUsername(), curData.getSex(), curData.getPhone(),
                    curData.getEmail(), curData.getAvatar());
        }
        tabView.onUserInfoResult(userInfo);
    }


    @Override
    public void doGetGroupMembers(String groupId) {
        model.doGetGroupMembers(groupId);
    }

    @Override
    public void doGetUserInfo(String userId) {
        model.doGetUserInfo(userId);
    }

    public ArrayList<GroupMember> getMembers() {
        return model.getMembers();
    }
}
