package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.CreateCollectionBean;

public class CreateCollectionEvent {

    public CreateCollectionBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(CreateCollectionBean.ResResultBean result) {
        this.result = result;
    }

    private CreateCollectionBean.ResResultBean result;

    public CreateCollectionEvent(CreateCollectionBean.ResResultBean bean) {
        result = bean;
    }

}
