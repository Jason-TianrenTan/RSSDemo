package com.example.chidori.proxytestapp.Contract;

import com.example.chidori.proxytestapp.Events.UpdateAccountEvent;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;
import com.example.chidori.proxytestapp.Utils.Beans.UpdateBean;

public interface Contract {


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


    //注册
    interface IRegisterView {
        void onRegisterResult(RegisterBean.ResResultBean resResultBean);
    }

    interface IRegisterModel {
        void doRegister(String username, String password, String phone, String email, int sex);
    }

    interface IRegisterPresenter {

    }


    interface ILoginView {
        void onLoginCall(LoginBean.ResResultBean bean);
    }

    interface ILoginModel {

        void doLogin(String username, String password);
    }

    interface ILoginPresenter {
        void doLogin(String username, String password);
    }

    //更新用户信息
    interface IUpdateView {
        void onUpdateResult(UpdateAccountEvent updateEvent);
    }

    interface IUpdateModel {
        void doUpdateUserInfo(String username, String password, String phone, String email, int sex, String userId);
    }

    interface IUpdatePresenter {

    }
}
