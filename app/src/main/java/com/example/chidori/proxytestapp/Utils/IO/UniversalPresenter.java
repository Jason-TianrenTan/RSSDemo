package com.example.chidori.proxytestapp.Utils.IO;

import android.widget.Toast;

import com.example.chidori.proxytestapp.Events.CreateCollectionEvent;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.Events.RegisterEvent;
import com.example.chidori.proxytestapp.Events.UpdateAccountEvent;
import com.example.chidori.proxytestapp.Utils.Beans.CreateCollectionBean;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;
import com.example.chidori.proxytestapp.Utils.Beans.UpdateBean;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UniversalPresenter extends BasePresenter{

    //userid = 061583de-12be-4116-ac58-e7343aa7f024
    //collectionid = 28084a85-6d10-4693-bd27-d031b2478bcf
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

    //更新
    public void UpdateByRetrofit(String username, String password, String phone, String email, int sex, String userID) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("username", username);
            jsonObject.addProperty("password", password);
            jsonObject.addProperty("phone", phone);
            jsonObject.addProperty("email", email);
            jsonObject.addProperty("sex", sex);

            JsonObject userJson = new JsonObject();
            userJson.addProperty("userId", userID);
            wrapper.add("reqUserInfo", userJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .requestUpdate(wrapper)
                .map(new Function<UpdateBean, UpdateBean.ResResultBean>() {
                    @Override
                    public UpdateBean.ResResultBean apply(UpdateBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(UpdateBean.ResResultBean result) {
                        EventBus.getDefault().post(new UpdateAccountEvent(result));
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

    //创建收藏夹
    public void CreateCollection(String name, String desc, int pbStatus, String userId) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("name", name);
            jsonObject.addProperty("description", desc);
            jsonObject.addProperty("publicStatus", pbStatus);

            JsonObject userJson = new JsonObject();
            userJson.addProperty("userId", userId);
            wrapper.add("reqUserInfo", userJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .createCollection(wrapper)
                .map(new Function<CreateCollectionBean, CreateCollectionBean.ResResultBean>() {
                    @Override
                    public CreateCollectionBean.ResResultBean apply(CreateCollectionBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreateCollectionBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(CreateCollectionBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new CreateCollectionEvent(result));
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

    //
    public void GetEntryListPublicToAll(String from) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("device", "mobile");
            jsonObject.addProperty("from", from);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .getPublicEntriesToAll(wrapper)
                .map(new Function<EntryListBean, EntryListBean.ResResultBean>() {
                    @Override
                    public EntryListBean.ResResultBean apply(EntryListBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EntryListBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(EntryListBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new EntryListEvent(result));
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


    //
    public void GetEntryListPublicToGroup(String from) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("device", "mobile");
            jsonObject.addProperty("from", from);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .getPublicEntriesToGroup(wrapper)
                .map(new Function<EntryListBean, EntryListBean.ResResultBean>() {
                    @Override
                    public EntryListBean.ResResultBean apply(EntryListBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EntryListBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(EntryListBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new EntryListEvent(result));
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
