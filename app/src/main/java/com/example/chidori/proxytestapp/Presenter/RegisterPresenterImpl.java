package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.RegisterEvent;
import com.example.chidori.proxytestapp.Model.RegisterModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class RegisterPresenterImpl {

    private RegisterModelImpl model;
    //View
    private Contract.IRegisterView registerView;

    public RegisterPresenterImpl() {
        model = new RegisterModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IRegisterView view) {
        registerView = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterCall(RegisterEvent registerEvent) {
        //处理注册结果
    }
}
