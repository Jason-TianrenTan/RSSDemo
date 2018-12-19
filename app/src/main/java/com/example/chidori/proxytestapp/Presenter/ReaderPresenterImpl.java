package com.example.chidori.proxytestapp.Presenter;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.URLEvent;
import com.example.chidori.proxytestapp.Model.LoginModelImpl;
import com.example.chidori.proxytestapp.Model.ReaderModelImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ReaderPresenterImpl implements Contract.IReaderPresenter {

    //Model
    private ReaderModelImpl model;
    //View
    private Contract.IReaderView readerView;

    public ReaderPresenterImpl() {
        model = new ReaderModelImpl();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUrlEventCall(URLEvent urlEvent) {
        //处理
        readerView.onTitleFound(urlEvent.getTitle());
    }

    public void attachView(Contract.IReaderView view) {
        readerView = view;
    }

    @Override
    public void doLoadURL(String url) {
        model.doLoadURL(url);
    }

    @Override
    public String getNickname() {
        return model.getNickname();
    }

    @Override
    public String getTitle() {
        return model.getTitleName();
    }

    @Override
    public void setTitle(String title) {
        model.setTitleName(title);
    }
}
