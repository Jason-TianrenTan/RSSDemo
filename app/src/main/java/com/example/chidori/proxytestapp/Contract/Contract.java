package com.example.chidori.proxytestapp.Contract;

public interface Contract {

    interface ILoginView {

    }

    interface ILoginModel {

        void doLogin(String username, String password);
    }

    interface ILoginPresenter {

    }

    interface ILoginCallback {
        void Ok();
        void Error(String errMsg);
    }
}
