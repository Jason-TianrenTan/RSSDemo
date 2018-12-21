package com.example.chidori.proxytestapp.Contract;

import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;

public interface Contract {

    interface ILoginView {
        void onLoginCall(LoginBean.ResResultBean bean);
    }

    interface ILoginModel {

        void doLogin(String username, String password);
    }

    interface ILoginPresenter {
        void doLogin(String username, String password);
    }

}
