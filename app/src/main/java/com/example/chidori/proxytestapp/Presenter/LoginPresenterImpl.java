package com.example.chidori.proxytestapp.Presenter;


import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.Model.LoginModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.internal.EverythingIsNonNull;

public class LoginPresenterImpl implements Contract.ILoginPresenter {

    //Model
    private LoginModelImpl model;
    //View
    private Contract.ILoginView loginView;

    public LoginPresenterImpl() {
        model = new LoginModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.ILoginView view) {
        loginView = view;
    }

    @Override
    public void doLogin(String username, String password) {
        model.doLogin(username, password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventCall(LoginEvent loginEvent) {

    }
}
