package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.UpdateBean;

public class UpdateAccountEvent {
    public UpdateBean.ResResultBean getResult() {
        return result;
    }

    public void setResult(UpdateBean.ResResultBean result) {
        this.result = result;
    }

    private UpdateBean.ResResultBean result;

    public UpdateAccountEvent(UpdateBean.ResResultBean bean) {
        result = bean;
    }

}
