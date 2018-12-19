package com.example.chidori.proxytestapp.Contract;

public interface Contract {

    //登录
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


    //阅读器
    interface IReaderView {
        void onTitleFound(String title);
    }

    interface IReaderModel {
        void doLoadURL(String url);
    }

    interface IReaderPresenter {
        void doLoadURL(String url);
        String getTitle();
        String getNickname();
        void setTitle(String title);
    }
}
