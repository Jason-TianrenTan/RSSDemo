package com.example.chidori.proxytestapp.Presenter;


import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.CollectionListEvent;
import com.example.chidori.proxytestapp.Events.CreateCollectionEvent;
import com.example.chidori.proxytestapp.Events.CreateGroupEvent;
import com.example.chidori.proxytestapp.Events.DeleteSourceEvent;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Events.SourceListEvent;
import com.example.chidori.proxytestapp.Model.ListModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class ListPresenterImpl implements Contract.IListPresenter {

    public ListPresenterImpl() {
        model = new ListModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }
    private ListModelImpl model;

    private Contract.IListView listView;

    public ArrayList<Collection> getCollections() {
        return model.getCollections();
    }

    public ArrayList<Source> getSources() {
        return model.getSources();
    }

    public ArrayList<Entry> getEntries(){
        return model.getEntries();
    }

    public void attachView(Contract.IListView view) {
        listView = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionsCall(CollectionListEvent collectionListEvent) {
        if (collectionListEvent.getResult().isIsSuccess()) {
            model.setupCollections(collectionListEvent.getResult());
            listView.onUserCollectionsCall("success");
        } else listView.onUserCollectionsCall("failure");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionDeleted(ModifyCollectionEvent modifyCollectionEvent) {
        boolean success = modifyCollectionEvent.getResult().isIsSuccess();
        if (modifyCollectionEvent.getType() == ModifyCollectionEvent.EventType.DELETE) {
            if (success)
                listView.onCollectionDeleted("success");
            else listView.onCollectionDeleted("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSourcesGet(SourceListEvent sourceListEvent) {
        boolean success = sourceListEvent.getResult().isIsSuccess();
        if (success) {
            model.setupSources(sourceListEvent.getResult());
            listView.onUserSourcesRetrieved("success");
        } else listView.onUserSourcesRetrieved("failure");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSourceDeleted(DeleteSourceEvent deleteSourceEvent) {
        if (deleteSourceEvent.getResult().isIsSuccess())
            listView.onSourceDeleted("success");
        else listView.onSourceDeleted("false");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntriesGet(EntryListEvent entryListEvent) {
        if (entryListEvent.getType() == EntryListEvent.EventType.LIST_BY_SOURCE) {
            if (entryListEvent.getResult().isIsSuccess()) {
                model.setupEntries(entryListEvent.getResult());
                listView.onEntriesBySourceRetrieved("success");
            } else listView.onEntriesBySourceRetrieved("failure");
        } else if (entryListEvent.getType() == EntryListEvent.EventType.LIST_COLLECTION) {
            if (entryListEvent.getResult().isIsSuccess()) {
                model.setupEntries(entryListEvent.getResult());
                listView.onEntriesByCollectionRetrieved("success");
            } else listView.onEntriesByCollectionRetrieved("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntryAddedToCollection(ModifyCollectionEvent modifyCollectionEvent) {
        if (modifyCollectionEvent.getType() == ModifyCollectionEvent.EventType.ADD_ENTRY) {
            if (modifyCollectionEvent.getResult().isIsSuccess())
                listView.onEntryAddedToCollection("success");
            else listView.onEntryAddedToCollection("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionCreated(CreateCollectionEvent createCollectionEvent) {
        if (createCollectionEvent.getResult().isIsSuccess())
            listView.onCollectionCreated("success");
        else listView.onCollectionCreated("failure");
    }


    @Override
    public void doGetUserCollections() {
        model.doGetUserCollections();
    }

    @Override
    public void deleteCollection(String collectionId) {
        model.deleteCollection(collectionId);
    }

    @Override
    public void doGetUserSources() {
        model.doGetUserSources();
    }

    @Override
    public void deleteSource(String sourceId) {
        model.deleteSource(sourceId);
    }

    @Override
    public void doGetEntriesBySource(String sourceId) {
        model.doGetEntriesBySource(sourceId);
    }

    @Override
    public void doGetEntriesByCollection(String collectionId) {
        model.doGetEntriesByCollection(collectionId);
    }

    @Override
    public void doAddEntryToCollection(String collectionId, String entryId) {
        model.doAddEntryToCollection(collectionId, entryId);
    }

    @Override
    public void doCreateCollection(String name, String desc, int publicStatus) {
        model.doCreateCollection(name, desc, publicStatus);
    }

}
