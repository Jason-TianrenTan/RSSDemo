package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.ModifyCollectionBean;

public class ModifyCollectionEvent {

    public ModifyCollectionBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(ModifyCollectionBean.ResResultBean result) {
        this.result = result;
    }

    private ModifyCollectionBean.ResResultBean result;

    public ModifyCollectionEvent(ModifyCollectionBean.ResResultBean bean) {
        result = bean;
    }
}
