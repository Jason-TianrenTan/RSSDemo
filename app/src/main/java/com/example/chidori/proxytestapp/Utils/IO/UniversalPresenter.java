package com.example.chidori.proxytestapp.Utils.IO;

import com.example.chidori.proxytestapp.Events.CreateCollectionEvent;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Events.RegisterEvent;
import com.example.chidori.proxytestapp.Events.UpdateAccountEvent;
import com.example.chidori.proxytestapp.Utils.Beans.CreateCollectionBean;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.ModifyCollectionBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;
import com.example.chidori.proxytestapp.Utils.Beans.UpdateBean;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UniversalPresenter extends BasePresenter{

    //userid = 061583de-12be-4116-ac58-e7343aa7f024
    //collectionid = a6c04078-7da9-44e3-8817-0fb216cf830a
    //entryid = "0ac182de-0d69-4baf-8165-7ce530dcac1e"
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


    //修改收藏夹状态
    public void ModifyCollectionStatus(String collectionId, int status) {
        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("collectionId", collectionId);
            jsonObject.addProperty("status", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .changeCollectionStatus(wrapper)
                .map(new Function<ModifyCollectionBean, ModifyCollectionBean.ResResultBean>() {
                    @Override
                    public ModifyCollectionBean.ResResultBean apply(ModifyCollectionBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifyCollectionBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(ModifyCollectionBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new ModifyCollectionEvent(result));
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


    //清空收藏夹
    public void ClearCollection(String collectionId) {
        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("collectionId", collectionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .clearCollection(wrapper)
                .map(new Function<ModifyCollectionBean, ModifyCollectionBean.ResResultBean>() {
                    @Override
                    public ModifyCollectionBean.ResResultBean apply(ModifyCollectionBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifyCollectionBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(ModifyCollectionBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new ModifyCollectionEvent(result));
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


    //添加文章
    public void AddEntryToCollection(String collectionId, String entryId) {
        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("collectionId", collectionId);
            jsonObject.addProperty("entryId", entryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .addEntryToCollection(wrapper)
                .map(new Function<ModifyCollectionBean, ModifyCollectionBean.ResResultBean>() {
                    @Override
                    public ModifyCollectionBean.ResResultBean apply(ModifyCollectionBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifyCollectionBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(ModifyCollectionBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new ModifyCollectionEvent(result));
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
