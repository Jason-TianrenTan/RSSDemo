package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.URLEvent;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModelImpl implements Contract.ILoginModel {


    @Override
    public void doLogin(String username, String password) {
        new UniversalPresenter().LoginByRetrofit(username, password);
    }
}
