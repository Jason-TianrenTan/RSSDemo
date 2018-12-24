package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.CollectionListEvent;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.SaveRSSEvent;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRSSCall(SaveRSSEvent saveRSSEvent) {
        //处理登录结果
        menuView.onLinkResult(saveRSSEvent.getResult());
    }
    @Override
    public void doAddRSSFromLink(String link) {
        model.doAddRSSFromLink(link);
    }

    @Override
    public void doGetUserCollections() {
        model.doGetUserCollections();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionsCall(CollectionListEvent collectionListEvent) {
        if (collectionListEvent.getResult().isIsSuccess()) {
            model.setupCollections(collectionListEvent.getResult());
            menuView.onUserCollectionsCall("success");
        } else menuView.onUserCollectionsCall("failure");
    }

    public ArrayList<Collection> getCollections() {
        return model.getCollections();
    }

    @Override
    public void doGetEntriesByCollection(String collectionId) {
        model.doGetEntriesByCollection(collectionId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntriesGet(EntryListEvent entryListEvent) {
        if (entryListEvent.getType() == EntryListEvent.EventType.LIST_COLLECTION) {
            if (entryListEvent.getResult().isIsSuccess()) {
                model.setupEntries(entryListEvent.getResult());
                menuView.onEntriesByCollectionRetrieved("success");
            } else menuView.onEntriesByCollectionRetrieved("failure");
        }
    }

    public ArrayList<Entry> getEntries(){
        return model.getEntries();
    }

}
