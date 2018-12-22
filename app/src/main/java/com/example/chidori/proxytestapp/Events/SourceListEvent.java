package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.SourceListBean;

public class SourceListEvent {

    public SourceListBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(SourceListBean.ResResultBean result) {
        this.result = result;
    }

    private SourceListBean.ResResultBean result;

    public SourceListEvent(SourceListBean.ResResultBean bean) {
        result = bean;
    }


}
