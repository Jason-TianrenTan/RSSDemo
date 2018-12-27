package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.CollectionListEvent;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.SaveRSSEvent;
import com.example.chidori.proxytestapp.Events.SourceListEvent;
import com.example.chidori.proxytestapp.Model.MenuModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MenuPresenterImpl implements Contract.IMenuPresenter {

    //Model
    private MenuModelImpl model;
    //View
    private Contract.IMenuView menuView;

    public MenuPresenterImpl() {
        model = new MenuModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IMenuView view) {
        menuView = view;
    }

    public ArrayList<Collection> getCollections() {
        return model.getCollections();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRSSCall(SaveRSSEvent saveRSSEvent) {
        menuView.onLinkResult(saveRSSEvent.getResult());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionsCall(CollectionListEvent collectionListEvent) {
        if(collectionListEvent.getType()==CollectionListEvent.EventType.COLLECTION_LIST){
            if (collectionListEvent.getResult().isIsSuccess()) {
                model.setupCollections(collectionListEvent.getResult());
                menuView.onUserCollectionsCall("success");
            } else menuView.onUserCollectionsCall("failure");
        }
    }

    @Override
    public void doAddRSSFromLink(String link) {
        model.doAddRSSFromLink(link);
    }

    @Override
    public void doGetUserCollections() {
        model.doGetUserCollections();
    }

}
