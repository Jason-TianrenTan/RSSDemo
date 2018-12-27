package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.DeleteSourceEvent;
import com.example.chidori.proxytestapp.Events.SourceListEvent;
import com.example.chidori.proxytestapp.Model.SourceFragmentModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class SourceFragmentPresenter implements Contract.ISourceFragmentPresenter{

    private SourceFragmentModelImpl model;

    private Contract.ISourceFragmentView sourceFragmentView;

    public SourceFragmentPresenter() {
        model = new SourceFragmentModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.ISourceFragmentView view) {
        sourceFragmentView = view;
    }

    public ArrayList<Source> getSources() {
        return model.getSources();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSourceDeleted(DeleteSourceEvent deleteSourceEvent){
        if (deleteSourceEvent.getResult().isIsSuccess()) {
            sourceFragmentView.onSourceDeleted("success");
        }
        else sourceFragmentView.onSourceDeleted("failure");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSourcesGet(SourceListEvent sourceListEvent) {
        if (sourceListEvent.getResult().isIsSuccess()) {
            model.setupSources(sourceListEvent.getResult());
            sourceFragmentView.onUserSourcesRetrieved("success");
        } else sourceFragmentView.onUserSourcesRetrieved("failure");
    }

    @Override
    public void deleteSource(String sourceId) {
        model.deleteSource(sourceId);
    }

    @Override
    public void doGetUserSources() {
        model.doGetUserSources();
    }
}
