package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.CreateCollectionEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Model.CollectionModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CollectionPresenterImpl implements Contract.ICollectionPresenter {

    private CollectionModelImpl model;

    private Contract.ICollectionView collectionView;

    public CollectionPresenterImpl() {
        model = new CollectionModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.ICollectionView view) {
        collectionView = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionDeleted(ModifyCollectionEvent modifyCollectionEvent) {
        boolean success = modifyCollectionEvent.getResult().isIsSuccess();
        if (modifyCollectionEvent.getType() == ModifyCollectionEvent.EventType.DELETE) {
            if (success)
                collectionView.onCollectionDeleted("success");
            else collectionView.onCollectionDeleted("failure");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectionCreated(CreateCollectionEvent createCollectionEvent) {
        if (createCollectionEvent.getResult().isIsSuccess())
            collectionView.onCollectionCreated("success");
        else collectionView.onCollectionCreated("failure");
    }

    @Override
    public void deleteCollection(String collectionId) {
        model.deleteCollection(collectionId);
    }

    @Override
    public void doCreateCollection(String name, String desc, int publicStatus) {
        model.doCreateCollection(name, desc, publicStatus);
    }
}
