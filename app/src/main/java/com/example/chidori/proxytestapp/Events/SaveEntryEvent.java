package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.SaveEntryBean;

public class SaveEntryEvent {

    public SaveEntryBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(SaveEntryBean.ResResultBean result) {
        this.result = result;
    }

    private SaveEntryBean.ResResultBean result;

    public SaveEntryEvent(SaveEntryBean.ResResultBean bean) {
        result = bean;
    }


}
