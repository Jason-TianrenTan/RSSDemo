package com.example.chidori.proxytestapp.Contract;

import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;

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

    //注册

    interface IRegisterView {

        void onRegisterResult(RegisterBean.ResResultBean resResultBean);

    }



    interface IRegisterModel {

        void doRegister(String username, String password, String phone, String email, int sex);

    }



    interface IRegisterPresenter {



    }

}
