package com.example.chidori.proxytestapp.Utils.IO;

import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;

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
        ApiManager.getInstance()
                .getRetrofitService()
                .requestLogin(username, password)
                .map(new Function<LoginBean, String>() {
                    @Override
                    public String apply(LoginBean bean) {
                        return bean.getResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(String s) {
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
