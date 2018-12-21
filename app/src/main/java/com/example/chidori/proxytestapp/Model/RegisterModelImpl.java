package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

public class RegisterModelImpl implements Contract.IRegisterModel {





    @Override

    public void doRegister(String username, String password, String phone, String email, int sex) {

        new UniversalPresenter().RegisterByRetrofit(username, password, phone, email, sex);

    }

}
