package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.DeleteSourceEvent;
import com.example.chidori.proxytestapp.Events.SaveRSSEvent;
import com.example.chidori.proxytestapp.Model.SourceModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SourcePresenterImpl implements Contract.ISourcePresenter {

    private SourceModelImpl model;

    private Contract.ISourceView sourceView;

    public SourcePresenterImpl() {
        model = new SourceModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    public void attachView(Contract.ISourceView view) {
        sourceView = view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSourceDeleted(DeleteSourceEvent deleteSourceEvent){
        if (deleteSourceEvent.getResult().isIsSuccess()) {
            sourceView.onSourceDeleted("success");
        }
        else sourceView.onSourceDeleted("failure");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRSSCall(SaveRSSEvent saveRSSEvent) {
        sourceView.onLinkResult(saveRSSEvent.getResult());
    }

    @Override
    public void deleteSource(String sourceId) {
        model.deleteSource(sourceId);
    }

    @Override
    public void doAddRSSFromLink(String link) {
        model.doAddRSSFromLink(link);
    }
}
