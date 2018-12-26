package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Events.SourceListEvent;
import com.example.chidori.proxytestapp.Model.PublicEntryFragmentModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class PublicEntryFragmentPresenter implements Contract.IPublicEntryFragmentPresenter {

    private PublicEntryFragmentModelImpl model;

    private Contract.IPublicEntryFragmentView publicEntryFragmentView;

    public PublicEntryFragmentPresenter() {
        model = new PublicEntryFragmentModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IPublicEntryFragmentView view) {
        publicEntryFragmentView = view;
    }

    public ArrayList<Entry> getEntries() {
        return model.getEntries();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntryAddedToCollection(ModifyCollectionEvent modifyCollectionEvent) {
        if (modifyCollectionEvent.getType() == ModifyCollectionEvent.EventType.ADD_ENTRY) {
            if (modifyCollectionEvent.getResult().isIsSuccess())
                publicEntryFragmentView.onEntryAdded("success");
            else publicEntryFragmentView.onEntryAdded("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntriesGet(EntryListEvent entryListEvent) {
        if (entryListEvent.getType() == EntryListEvent.EventType.PUBLIC_TO_ALL) {
            if (entryListEvent.getResult().isIsSuccess()) {
                model.setupEntries(entryListEvent.getResult());
                publicEntryFragmentView.onPublicEntriesRetrieved("success");
            } else publicEntryFragmentView.onPublicEntriesRetrieved("failure");
        }
    }

    @Override
    public void doGetPublicEntries() {
        model.doGetPublicEntries();
    }

    @Override
    public void doAddEntryToCollection(String collectionId, String entryId) {
        model.doAddEntryToCollection(collectionId, entryId);
    }
}
