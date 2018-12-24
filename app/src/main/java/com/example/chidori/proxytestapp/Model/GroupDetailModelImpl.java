package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

public class GroupDetailModelImpl implements Contract.IGroupDetailModel {

    @Override
    public void doEnterGroup(String groupId) {
        new UniversalPresenter().EnterGroup(groupId, Config.userId);
    }

    @Override
    public void doQuitGroup(String groupId) {
        new UniversalPresenter().QuitGroup(groupId, Config.userId);
    }
}
