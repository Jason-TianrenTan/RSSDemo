package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.CollectionListEvent;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Model.TabACModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class TabACPresenterImpl implements Contract.ITabACPresenter {

    TabACModelImpl model;

    public TabACPresenterImpl() {
        model = new TabACModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    Contract.ITabACView tabACView;

    public void attachView(Contract.ITabACView view) {
        tabACView = view;
    }

    @Override
    public void doGetGroupEntries(String groupId) {
        model.doGetGroupEntries(groupId);
    }

    @Override
    public void doGetGroupCollections(String groupId) {
        model.doGetGroupCollections(groupId);
    }

    @Override
    public void doAddEntry(String collectionId, String entryId) {
        model.doAddEntry(collectionId, entryId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntriesGet(EntryListEvent entryListEvent) {
        if (entryListEvent.getType() == EntryListEvent.EventType.PUBLIC_TO_GROUP) {
            if (entryListEvent.getResult().isIsSuccess()) {
                model.setupEntries(entryListEvent.getResult());
                tabACView.onGroupEntriesRetrieved("success");
            } else tabACView.onGroupEntriesRetrieved("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionsGet(CollectionListEvent collectionListEvent) {
        if (collectionListEvent.getType() == CollectionListEvent.EventType.GROUP_PUBLIC) {
            if (collectionListEvent.getResult().isIsSuccess()) {
                model.setupCollections(collectionListEvent.getResult());
                tabACView.onGroupCollectionsRetrieved("success");
            } else tabACView.onGroupCollectionsRetrieved("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntryAddedToCollection(ModifyCollectionEvent modifyCollectionEvent) {
        if (modifyCollectionEvent.getType() == ModifyCollectionEvent.EventType.ADD_ENTRY) {
            if (modifyCollectionEvent.getResult().isIsSuccess())
                tabACView.onEntryAdded("success");
            else tabACView.onEntryAdded("failure");
        }
    }

    public ArrayList<Entry> getEntries() {
        return model.getEntries();
    }

    public ArrayList<Collection> getCollections(){
        return model.getCollections();
    }
}
