package com.example.chidori.proxytestapp.Utils.IO;

import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.Events.RegisterEvent;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;
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

    public void LoginByRetrofit(String username, String password) {



        JsonObject wrapper = new JsonObject();

        try {

            JsonObject jsonObject = new JsonObject();

            wrapper.add("reqParam", jsonObject);

            jsonObject.addProperty("username", username);

            jsonObject.addProperty("password", password);

        } catch (Exception e) {

            e.printStackTrace();

        }

        ApiManager.getInstance()

                .getRSSRetrofitService()

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

                    public void onNext(LoginBean.ResResultBean result) {

                        System.out.println("result: " + result.isIsSuccess());

                        EventBus.getDefault().post(new LoginEvent(result));

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



    //注册

    public void RegisterByRetrofit(String username, String password, String phone, String email, int sex) {



        JsonObject wrapper = new JsonObject();

        try {

            JsonObject jsonObject = new JsonObject();

            wrapper.add("reqParam", jsonObject);

            jsonObject.addProperty("username", username);

            jsonObject.addProperty("password", password);

            jsonObject.addProperty("phone", phone);

            jsonObject.addProperty("email", email);

            jsonObject.addProperty("sex", sex);

        } catch (Exception e) {

            e.printStackTrace();

        }

        ApiManager.getInstance()

                .getRSSRetrofitService()

                .requestRegister(wrapper)

                .map(new Function<RegisterBean, RegisterBean.ResResultBean>() {

                    @Override

                    public RegisterBean.ResResultBean apply(RegisterBean bean) {

                        return bean.getResResult();

                    }

                }).subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<RegisterBean.ResResultBean>() {

                    @Override

                    public void onSubscribe(Disposable d) {

                        addDisposable(d);

                    }



                    @Override

                    public void onNext(RegisterBean.ResResultBean result) {

                        System.out.println("result: " + result.isIsSuccess());

                        EventBus.getDefault().post(new RegisterEvent(result));

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
