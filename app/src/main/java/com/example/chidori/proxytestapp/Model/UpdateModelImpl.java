package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

public class UpdateModelImpl implements Contract.IUpdateModel {

    @Override
    public void doUpdateUserInfo(String username, String password, String phone, String email, int sex, String userId) {
        new UniversalPresenter().UpdateByRetrofit(username, password, phone, email, sex, userId);
    }
}
