package com.example.chidori.proxytestapp.Model;

import android.content.SharedPreferences;

import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

public class MenuModelImpl implements Contract.IMenuModel {
    @Override
    public void doAddRSSFromLink(String link) {
        new UniversalPresenter().SaveSingleRSS(link, Config.userId);
    }
}
