package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Events.SourceListEvent;
import com.example.chidori.proxytestapp.Model.NavigationHomeModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class NavigationHomePresenter implements Contract.INavigationHomePresenter {

    NavigationHomeModelImpl model;

    Contract.INavigationHomeView navigationHomeView;

    public NavigationHomePresenter() {
        model = new NavigationHomeModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSourcesRetrieved(SourceListEvent sourceListEvent) {
        if (sourceListEvent.getResult().isIsSuccess()) {
            model.setupSources(sourceListEvent.getResult());
            navigationHomeView.onSourceGet("success");
        } else
            navigationHomeView.onSourceGet("failure");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntryAddedToCollection(ModifyCollectionEvent modifyCollectionEvent) {
        if (modifyCollectionEvent.getType() == ModifyCollectionEvent.EventType.ADD_ENTRY) {
            if (modifyCollectionEvent.getResult().isIsSuccess())
                navigationHomeView.onEntryAdded("success");
            else navigationHomeView.onEntryAdded("failure");
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntriesGet(EntryListEvent entryListEvent) {
        if (entryListEvent.getType() == EntryListEvent.EventType.PUBLIC_TO_ALL) {
            if (entryListEvent.getResult().isIsSuccess()) {
                model.setupEntries(entryListEvent.getResult());
                navigationHomeView.onPublicEntriesRetrieved("success");
            } else navigationHomeView.onPublicEntriesRetrieved("failure");
        }
    }

    public void attachView(Contract.INavigationHomeView view) {
        navigationHomeView = view;
    }

    @Override
    public void doGetSources(int type) {
        model.doGetSources(type);
    }

    @Override
    public void deleteSource(String sourceId) {
        model.deleteSource(sourceId);
    }

    @Override
    public void doGetPublicEntries() {
        model.doGetPublicEntries();
    }

    @Override
    public void doAddEntry(String collectionId, String entryId) {
        model.doAddEntry(collectionId, entryId);
    }

    public ArrayList<Entry> getEntries() {
        return model.getEntries();
    }

    public ArrayList<Source> getSources() {
        return model.getSources();
    }
}
