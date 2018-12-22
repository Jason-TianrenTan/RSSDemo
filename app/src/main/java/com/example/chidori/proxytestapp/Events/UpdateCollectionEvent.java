package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.UpdateCollectionBean;

public class UpdateCollectionEvent {

    public UpdateCollectionBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(UpdateCollectionBean.ResResultBean result) {
        this.result = result;
    }

    private UpdateCollectionBean.ResResultBean result;

    public UpdateCollectionEvent(UpdateCollectionBean.ResResultBean bean) {
        this.result = bean;
    }


}
