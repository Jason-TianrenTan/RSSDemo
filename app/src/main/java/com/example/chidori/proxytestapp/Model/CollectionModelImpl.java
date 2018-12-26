package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

public class CollectionModelImpl implements Contract.ICollectionModel {
    @Override
    public void deleteCollection(String collectionId) {
        new UniversalPresenter().DeleteCollection(collectionId);
    }

    @Override
    public void doCreateCollection(String name, String desc, int publicStatus) {
        new UniversalPresenter().CreateCollection(name, desc, publicStatus, Config.userId);
    }
}
