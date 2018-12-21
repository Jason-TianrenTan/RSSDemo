package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;

public class EntryListEvent {

    public EntryListBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(EntryListBean.ResResultBean result) {
        this.result = result;
    }

    private EntryListBean.ResResultBean result;

    public EntryListEvent(EntryListBean.ResResultBean bean) {
        result = bean;
    }
}
