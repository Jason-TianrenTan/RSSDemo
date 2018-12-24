package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

public class GroupModelImpl implements Contract.IGroupModel {
    @Override
    public void doCreateGroup(String groupName, String desc) {
        new UniversalPresenter().CreateGroup(groupName, desc, Config.userId);
    }
}
