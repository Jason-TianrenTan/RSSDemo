package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.SaveRSSEvent;
import com.example.chidori.proxytestapp.Model.MenuModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MenuPresenterImpl implements Contract.IMenuPresenter {

    //Model
    private MenuModelImpl model;
    //View
    private Contract.IMenuView menuView;

    public MenuPresenterImpl() {
        model = new MenuModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IMenuView view) {
        menuView = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRSSCall(SaveRSSEvent saveRSSEvent) {
        //处理登录结果
        menuView.onLinkResult(saveRSSEvent.getResult());
    }
    @Override
    public void doAddRSSFromLink(String link) {
        model.doAddRSSFromLink(link);
    }
}
