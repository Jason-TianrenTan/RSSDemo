package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

public class SourceModelImpl implements Contract.ISourceModel {

    @Override
    public void doAddRSSFromLink(String link) {
        new UniversalPresenter().SaveSingleRSS(link, Config.userId);
    }

    @Override
    public void deleteSource(String sourceId) {
        new UniversalPresenter().DeleteRSS(sourceId);
    }

}
