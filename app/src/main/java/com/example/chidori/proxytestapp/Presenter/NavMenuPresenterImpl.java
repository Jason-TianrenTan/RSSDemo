package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.GroupResultEvent;
import com.example.chidori.proxytestapp.Model.NavMenuModelImpl;
import com.example.chidori.proxytestapp.Utils.Beans.SearchGroupBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class NavMenuPresenterImpl implements Contract.INavMenuPresenter {

    NavMenuModelImpl model;

    public NavMenuPresenterImpl() {
        model = new NavMenuModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    Contract.INavMenuView navMenuView;

    public void attachView(Contract.INavMenuView view) {
        navMenuView = view;
    }

    @Override
    public void doSearchGroup(String keyword) {
        model.doSearchGroup(keyword);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGroupSearchRes(GroupResultEvent groupResultEvent) {
        if (groupResultEvent.getResult().isIsSuccess()) {
            model.setupGroups(groupResultEvent.getResult());
            navMenuView.onGroupSearchResult("success");
        } else navMenuView.onGroupSearchResult("failure");
    }

    public ArrayList<Group> getGroups() {
        return model.getGroups();
    }
}
