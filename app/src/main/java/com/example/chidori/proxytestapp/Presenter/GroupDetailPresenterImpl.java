package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Model.GroupDetailModelImpl;

import org.greenrobot.eventbus.EventBus;

public class GroupDetailPresenterImpl implements Contract.IGroupDetailPresenter {

    GroupDetailModelImpl model;

    Contract.IGroupDetailView detailView;

    public void attachView(Contract.IGroupDetailView view) {
        detailView = view;
    }

    public GroupDetailPresenterImpl() {
        model = new GroupDetailModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void doEnterGroup(String groupId) {
        model.doEnterGroup(groupId);
    }

    @Override
    public void doQuitGroup(String groupId) {
        model.doQuitGroup(groupId);
    }
}
