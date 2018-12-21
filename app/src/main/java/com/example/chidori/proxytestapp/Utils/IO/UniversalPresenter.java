package com.example.chidori.proxytestapp.Utils.IO;

import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UniversalPresenter extends BasePresenter{

    //登录
    public void loginByRetrofit(String username, String password) {
        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("username", username);
            jsonObject.addProperty("password", password);
            wrapper.add("reqParam", jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRetrofitService()
                .requestLogin(wrapper)
                .map(new Function<LoginBean, LoginBean.ResResultBean>() {
                    @Override
                    public LoginBean.ResResultBean apply(LoginBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(LoginBean.ResResultBean s) {
                        System.out.println("post");
                        EventBus.getDefault().post(new LoginEvent(s));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
