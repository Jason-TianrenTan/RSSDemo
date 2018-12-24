package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.SearchGroupBean;

public class GroupResultEvent {

    public SearchGroupBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(SearchGroupBean.ResResultBean result) {
        this.result = result;
    }

    private SearchGroupBean.ResResultBean result;

    public GroupResultEvent(SearchGroupBean.ResResultBean bean) {
        this.result = bean;
    }

}
