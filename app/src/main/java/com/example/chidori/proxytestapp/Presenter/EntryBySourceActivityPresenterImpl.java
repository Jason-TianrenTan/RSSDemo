package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Model.EntryBySourceActivityModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class EntryBySourceActivityPresenterImpl implements Contract.IEntryBySourceActivityPresenter {

    private EntryBySourceActivityModelImpl model;

    private Contract.IEntryBySourceActivityView entryBySourceActivityView;

    public EntryBySourceActivityPresenterImpl() {
        model = new EntryBySourceActivityModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IEntryBySourceActivityView view) {
        entryBySourceActivityView = view;
    }

    public ArrayList<Entry> getEntries() {
        return model.getEntries();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntryAddedToCollection(ModifyCollectionEvent modifyCollectionEvent) {
        if (modifyCollectionEvent.getType() == ModifyCollectionEvent.EventType.ADD_ENTRY) {
            if (modifyCollectionEvent.getResult().isIsSuccess())
                entryBySourceActivityView.onEntryAdded("success");
            else entryBySourceActivityView.onEntryAdded("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntriesGet(EntryListEvent entryListEvent) {
        if (entryListEvent.getResult().isIsSuccess()) {
            model.setupEntries(entryListEvent.getResult());
            entryBySourceActivityView.onEntriesBySourceRetrieved("success");
        } else entryBySourceActivityView.onEntriesBySourceRetrieved("failure");
    }

    @Override
    public void doGetEntriesBySource(String sourceId) {
        model.doGetEntriesBySource(sourceId);
    }

    @Override
    public void doAddEntryToCollection(String collectionId, String entryId) {
        model.doAddEntryToCollection(collectionId, entryId);
    }
}
