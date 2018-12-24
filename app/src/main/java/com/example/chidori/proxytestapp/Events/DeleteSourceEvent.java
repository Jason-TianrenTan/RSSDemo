package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.DeleteSourceBean;

public class DeleteSourceEvent {
    public DeleteSourceBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(DeleteSourceBean.ResResultBean result) {
        this.result = result;
    }

    private DeleteSourceBean.ResResultBean result;

    public DeleteSourceEvent(DeleteSourceBean.ResResultBean bean) {
        result = bean;
    }
}
