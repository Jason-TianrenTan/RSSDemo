package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Model.GroupEntryFragmentModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class GroupEntryFragmentPresenterImpl implements Contract.IGroupEntryFragmentPresenter {

    private GroupEntryFragmentModelImpl model;

    private Contract.IGroupEntryFragmentView groupEntryFragmentView;

    public GroupEntryFragmentPresenterImpl() {
        model = new GroupEntryFragmentModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IGroupEntryFragmentView view) {
        groupEntryFragmentView = view;
    }

    public ArrayList<Entry> getEntries() {
        return model.getEntries();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntriesGet(EntryListEvent entryListEvent) {
        if (entryListEvent.getType() == EntryListEvent.EventType.PUBLIC_TO_GROUP) {
            if (entryListEvent.getResult().isIsSuccess()) {
                model.setupEntries(entryListEvent.getResult());
                groupEntryFragmentView.onGroupEntriesRetrieved("success");
            } else groupEntryFragmentView.onGroupEntriesRetrieved("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEntryAddedToCollection(ModifyCollectionEvent modifyCollectionEvent) {
        if (modifyCollectionEvent.getType() == ModifyCollectionEvent.EventType.ADD_ENTRY) {
            if (modifyCollectionEvent.getResult().isIsSuccess())
                groupEntryFragmentView.onEntryAdded("success");
            else groupEntryFragmentView.onEntryAdded("failure");
        }
    }

    @Override
    public void doGetGroupEntries(String groupId) {
        model.doGetGroupEntries(groupId);
    }

    @Override
    public void doAddEntryToCollection(String collectionId, String entryId) {
        model.doAddEntry(collectionId, entryId);
    }
}
