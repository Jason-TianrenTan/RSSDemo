package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Model.EntryByCollectionActivityModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class EntryByCollectionActivityPresenterImpl implements Contract.IEntryByCollectionActivityPresenter {

    private EntryByCollectionActivityModelImpl model;

    private Contract.IEntryByCollectionActivityView entryByCollectionActivityView;

    public EntryByCollectionActivityPresenterImpl() {
        model = new EntryByCollectionActivityModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IEntryByCollectionActivityView view) {
        entryByCollectionActivityView = view;
    }

    public ArrayList<Entry> getEntries() {
        return model.getEntries();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntryAddedToCollection(ModifyCollectionEvent modifyCollectionEvent) {
        if (modifyCollectionEvent.getType() == ModifyCollectionEvent.EventType.ADD_ENTRY) {
            if (modifyCollectionEvent.getResult().isIsSuccess())
                entryByCollectionActivityView.onEntryAdded("success");
            else entryByCollectionActivityView.onEntryAdded("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntriesGet(EntryListEvent entryListEvent) {
        if (entryListEvent.getResult().isIsSuccess()) {
            model.setupEntries(entryListEvent.getResult());
            entryByCollectionActivityView.onEntriesByCollectionRetrieved("success");
        } else entryByCollectionActivityView.onEntriesByCollectionRetrieved("failure");
    }

    @Override
    public void doGetEntriesByCollection(String collectionId) {
        model.doGetEntriesByCollection(collectionId);
    }

    @Override
    public void doAddEntryToCollection(String collectionId, String entryId) {
        model.doAddEntryToCollection(collectionId, entryId);
    }
}
