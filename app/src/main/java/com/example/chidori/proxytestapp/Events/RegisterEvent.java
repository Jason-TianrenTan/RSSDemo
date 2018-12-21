package com.example.chidori.proxytestapp.Events;

import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;

public class RegisterEvent {
    public RegisterBean.ResResultBean getResult() {
        return resultBean;
    }

    public void setResult(RegisterBean.ResResultBean resultBean) {
        this.resultBean = resultBean;
    }

    private RegisterBean.ResResultBean resultBean;

    public RegisterEvent(RegisterBean.ResResultBean bean) {
        resultBean = bean;
    }
}
