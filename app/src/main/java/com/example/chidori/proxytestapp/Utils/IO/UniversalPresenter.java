package com.example.chidori.proxytestapp.Utils.IO;

import com.example.chidori.proxytestapp.Events.AddSourceEvent;
import com.example.chidori.proxytestapp.Events.CollectionListEvent;
import com.example.chidori.proxytestapp.Events.CreateCollectionEvent;
import com.example.chidori.proxytestapp.Events.DeleteSourceEvent;
import com.example.chidori.proxytestapp.Events.EntryListEvent;
import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.Events.ModifyCollectionEvent;
import com.example.chidori.proxytestapp.Events.RefreshSourceEvent;
import com.example.chidori.proxytestapp.Events.RegisterEvent;
import com.example.chidori.proxytestapp.Events.SaveEntryEvent;
import com.example.chidori.proxytestapp.Events.SaveRSSEvent;
import com.example.chidori.proxytestapp.Events.SourceListEvent;
import com.example.chidori.proxytestapp.Events.UpdateAccountEvent;
import com.example.chidori.proxytestapp.Events.UpdateCollectionEvent;
import com.example.chidori.proxytestapp.Utils.Beans.AddSourceBean;
import com.example.chidori.proxytestapp.Utils.Beans.CollectionListBean;
import com.example.chidori.proxytestapp.Utils.Beans.CreateCollectionBean;
import com.example.chidori.proxytestapp.Utils.Beans.DeleteSourceBean;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.ModifyCollectionBean;
import com.example.chidori.proxytestapp.Utils.Beans.RefreshSourceBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;
import com.example.chidori.proxytestapp.Utils.Beans.SaveEntryBean;
import com.example.chidori.proxytestapp.Utils.Beans.SaveRSSBean;
import com.example.chidori.proxytestapp.Utils.Beans.SourceListBean;
import com.example.chidori.proxytestapp.Utils.Beans.UpdateBean;
import com.example.chidori.proxytestapp.Utils.Beans.UpdateCollectionBean;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class UniversalPresenter extends BasePresenter {



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


    //更新收藏夹
    public void UpdateCollection(String name, String desc, int pbStatus, String collectioniId) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("name", name);
            jsonObject.addProperty("description", desc);
            jsonObject.addProperty("publicStatus", pbStatus);
            jsonObject.addProperty("collectioinId", collectioniId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .updateCollection(wrapper)
                .map(new Function<UpdateCollectionBean, UpdateCollectionBean.ResResultBean>() {
                    @Override
                    public UpdateCollectionBean.ResResultBean apply(UpdateCollectionBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateCollectionBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(UpdateCollectionBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new UpdateCollectionEvent(result));
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
                        EventBus.getDefault().post(new EntryListEvent(result, EntryListEvent.EventType.PUBLIC_TO_ALL));
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
                        EventBus.getDefault().post(new EntryListEvent(result, EntryListEvent.EventType.PUBLIC_TO_GROUP));
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


    //根据来源获取文章
    public void GetEntryListBySource(String sourceId) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("sourceId", sourceId);
            jsonObject.addProperty("device", "mobile");

        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .getEntriesBySource(wrapper)
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
                        EventBus.getDefault().post(new EntryListEvent(result, EntryListEvent.EventType.LIST_BY_SOURCE));
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


    //根据来源获取文章
    public void GetEntryOfCollection(String collectionId) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("collectionId", collectionId);
            jsonObject.addProperty("device", "mobile");

        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .getEntriesOfCollection(wrapper)
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
                        EventBus.getDefault().post(new EntryListEvent(result, EntryListEvent.EventType.LIST_BY_SOURCE));
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
                        EventBus.getDefault().post(new ModifyCollectionEvent(result, ModifyCollectionEvent.EventType.CHANGE_STATUS));
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
                        EventBus.getDefault().post(new ModifyCollectionEvent(result, ModifyCollectionEvent.EventType.CLEAR));
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

    //删除收藏夹
    public void DeleteCollection(String collectionId) {
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
                .deleteCollection(wrapper)
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
                        EventBus.getDefault().post(new ModifyCollectionEvent(result, ModifyCollectionEvent.EventType.DELETE));
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
                        EventBus.getDefault().post(new ModifyCollectionEvent(result, ModifyCollectionEvent.EventType.ADD_ENTRY));
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


    //添加其他来源
    public void AddOtherSource(String name, String description, String link, String userId) {
        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("name", name);
            jsonObject.addProperty("description", description);
            jsonObject.addProperty("link", link);

            JsonObject userJson = new JsonObject();
            userJson.addProperty("userId", userId);
            wrapper.add("reqUserInfo", userJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .addOtherSource(wrapper)
                .map(new Function<AddSourceBean, AddSourceBean.ResResultBean>() {
                    @Override
                    public AddSourceBean.ResResultBean apply(AddSourceBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddSourceBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(AddSourceBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new AddSourceEvent(result));
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


    //获取指定publicStatus状态的Collections
    public void GetCollections(int publicStatus, String userId) {
        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("publicStatus", publicStatus);
            jsonObject.addProperty("userId", userId);
            jsonObject.addProperty("device", "mobile");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .getCollectionList(wrapper)
                .map(new Function<CollectionListBean, CollectionListBean.ResResultBean>() {
                    @Override
                    public CollectionListBean.ResResultBean apply(CollectionListBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectionListBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(CollectionListBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new CollectionListEvent(result, CollectionListEvent.EventType.COLLECTION_LIST));
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


    //获取所有Public的收藏夹
    public void GetAllPublicCollections() {
        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("device", "mobile");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .getAllPublic(wrapper)
                .map(new Function<CollectionListBean, CollectionListBean.ResResultBean>() {
                    @Override
                    public CollectionListBean.ResResultBean apply(CollectionListBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectionListBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(CollectionListBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new CollectionListEvent(result, CollectionListEvent.EventType.ALL_PUBLIC));
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


    //获取所有小组内Public的收藏夹
    public void GetGroupPublicCollections(String groupId) {
        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("groupId", groupId);
            jsonObject.addProperty("device", "mobile");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .getGroupPublic(wrapper)
                .map(new Function<CollectionListBean, CollectionListBean.ResResultBean>() {
                    @Override
                    public CollectionListBean.ResResultBean apply(CollectionListBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectionListBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(CollectionListBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new CollectionListEvent(result, CollectionListEvent.EventType.GROUP_PUBLIC));
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


    //移动文章
    public void MoveEntry(String entryId, String targetId) {
        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("entryId", entryId);
            jsonObject.addProperty("targetId", targetId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .moveEntry(wrapper)
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
                        EventBus.getDefault().post(new ModifyCollectionEvent(result, ModifyCollectionEvent.EventType.MOVE_ENTRY));
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


    //通过链接保存文章
    public void SaveEntryFromLink(String link, String collectionId, String description, String title) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("link", link);
            jsonObject.addProperty("collectionId", collectionId);
            jsonObject.addProperty("description", description);
            jsonObject.addProperty("title", title);

        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .saveEntryFromLink(wrapper)
                .map(new Function<SaveEntryBean, SaveEntryBean.ResResultBean>() {
                    @Override
                    public SaveEntryBean.ResResultBean apply(SaveEntryBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SaveEntryBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(SaveEntryBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new SaveEntryEvent(result));
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


    //保存单个RSS
    //通过链接保存文章
    public void SaveSingleRSS(String link, String userId) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("link", link);

            JsonObject userObject = new JsonObject();
            userObject.addProperty("userId", userId);
            wrapper.add("reqUserInfo", userObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .saveRSSLink(wrapper)
                .map(new Function<SaveRSSBean, SaveRSSBean.ResResultBean>() {
                    @Override
                    public SaveRSSBean.ResResultBean apply(SaveRSSBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SaveRSSBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(SaveRSSBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new SaveRSSEvent(result));
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


    //通过type获取源
    public void GetSources(int type, String userId) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("type", type);

            JsonObject userObject = new JsonObject();
            userObject.addProperty("userId", userId);
            wrapper.add("reqUserInfo", userObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .getSources(wrapper)
                .map(new Function<SourceListBean,SourceListBean.ResResultBean>() {
                    @Override
                    public SourceListBean.ResResultBean apply(SourceListBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SourceListBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(SourceListBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new SourceListEvent(result));
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



    //刷新单个RSS源
    public void RefreshSingleRSS(String sourceId) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("sourceId", sourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .refreshSingleRSS(wrapper)
                .map(new Function<RefreshSourceBean,RefreshSourceBean.ResResultBean>() {
                    @Override
                    public RefreshSourceBean.ResResultBean apply(RefreshSourceBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RefreshSourceBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(RefreshSourceBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new RefreshSourceEvent(result, RefreshSourceEvent.EventType.SINGLE_RSS));
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


    //删除源
    public void DeleteRSS(String sourceId) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqParam", jsonObject);
            jsonObject.addProperty("sourceId", sourceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .deleteRSS(wrapper)
                .map(new Function<DeleteSourceBean,DeleteSourceBean.ResResultBean>() {
                    @Override
                    public DeleteSourceBean.ResResultBean apply(DeleteSourceBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteSourceBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(DeleteSourceBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new DeleteSourceEvent(result));
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



    //刷新全部RSS源
    public void RefreshAllRSS(String userId) {

        JsonObject wrapper = new JsonObject();
        try {
            JsonObject jsonObject = new JsonObject();
            wrapper.add("reqUserInfo", jsonObject);
            jsonObject.addProperty("userId", userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ApiManager.getInstance()
                .getRSSRetrofitService()
                .refreshAllRSS(wrapper)
                .map(new Function<RefreshSourceBean,RefreshSourceBean.ResResultBean>() {
                    @Override
                    public RefreshSourceBean.ResResultBean apply(RefreshSourceBean bean) {
                        return bean.getResResult();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RefreshSourceBean.ResResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(RefreshSourceBean.ResResultBean result) {
                        System.out.println("result: " + result.isIsSuccess());
                        EventBus.getDefault().post(new RefreshSourceEvent(result, RefreshSourceEvent.EventType.BATCH_RSS));
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