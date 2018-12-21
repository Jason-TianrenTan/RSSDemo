package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

public class LoginModelImpl implements Contract.ILoginModel {

    @Override
    public void doLogin(String username, String password) {
        new UniversalPresenter().loginByRetrofit(username, password);
    }
}
