package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.GroupMember;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.CreateGroupEvent;
import com.example.chidori.proxytestapp.Events.GroupMemberEvent;
import com.example.chidori.proxytestapp.Events.GroupModifyEvent;
import com.example.chidori.proxytestapp.Events.UserDetailEvent;
import com.example.chidori.proxytestapp.Model.GroupDetailModelImpl;
import com.example.chidori.proxytestapp.Utils.Beans.GroupMemberBean;
import com.example.chidori.proxytestapp.Utils.Beans.UserDetailBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class GroupDetailPresenterImpl implements Contract.IGroupDetailPresenter {

    private GroupDetailModelImpl model;

    private Contract.IGroupDetailView detailView;

    public GroupDetailPresenterImpl() {
        model = new GroupDetailModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IGroupDetailView view) {
        detailView = view;
    }

    public ArrayList<GroupMember> getMembers() {
        return model.getMembers();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGroupEntered(GroupModifyEvent groupModifyEvent) {
        if (groupModifyEvent.getResult().isIsSuccess())
            detailView.onGroupEntered("success");
        else detailView.onGroupEntered("failure");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGroupQuited(GroupModifyEvent groupModifyEvent){
        if (groupModifyEvent.getResult().isIsSuccess())
            detailView.onGroupQuit("success");
        else detailView.onGroupQuit("failure");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGroupMemberResult(GroupMemberEvent groupMemberEvent) {
        GroupMemberBean.ResResultBean result = groupMemberEvent.getResult();
        if (result.isIsSuccess()) {
            model.setupMembers(result);
            detailView.onGroupMembersResult("success");
        } else detailView.onGroupMembersResult("failure");
    }

    @Override
    public void doEnterGroup(String groupId) {
        model.doEnterGroup(groupId);
    }

    @Override
    public void doQuitGroup(String groupId) {
        model.doQuitGroup(groupId);
    }

    @Override
    public void doGetGroupMembers(String groupId) {
        model.doGetGroupMembers(groupId);
    }
}
