package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.CreateGroupEvent;
import com.example.chidori.proxytestapp.Model.GroupModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

}
