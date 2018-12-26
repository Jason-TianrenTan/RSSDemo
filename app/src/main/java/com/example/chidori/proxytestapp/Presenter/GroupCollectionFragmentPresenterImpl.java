package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.CollectionListEvent;
import com.example.chidori.proxytestapp.Model.GroupCollectionFragmentModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class GroupCollectionFragmentPresenterImpl implements Contract.IGroupCollectionFragmentPresenter{

    private GroupCollectionFragmentModelImpl model;

    private Contract.IGroupCollectionFragmentView groupCollectionFragmentView;

    public GroupCollectionFragmentPresenterImpl() {
        model = new GroupCollectionFragmentModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.IGroupCollectionFragmentView view) {
        groupCollectionFragmentView = view;
    }

    public ArrayList<Collection> getCollections(){
        return model.getCollections();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionsGet(CollectionListEvent collectionListEvent) {
        if (collectionListEvent.getType() == CollectionListEvent.EventType.GROUP_PUBLIC) {
            if (collectionListEvent.getResult().isIsSuccess()) {
                model.setupCollections(collectionListEvent.getResult());
                groupCollectionFragmentView.onGroupCollectionsRetrieved("success");
            } else groupCollectionFragmentView.onGroupCollectionsRetrieved("failure");
        }
    }

    @Override
    public void doGetGroupCollections(String groupId) {
        model.doGetGroupCollections(groupId);
    }
}
