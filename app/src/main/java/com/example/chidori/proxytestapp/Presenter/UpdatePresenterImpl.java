package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.UpdateAccountEvent;
import com.example.chidori.proxytestapp.Model.UpdateModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class UpdatePresenterImpl implements Contract.IUpdatePresenter {
    private UpdateModelImpl model;
    //View
    private Contract.IUpdateView view;

    public UpdatePresenterImpl() {
        model = new UpdateModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IUpdateView view) {
        this.view = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateCall(UpdateAccountEvent updateEvent) {
        //处理结果

    }
}
