package com.example.chidori.proxytestapp.Utils.IO;

import com.example.chidori.proxytestapp.Utils.Beans.AddSourceBean;
import com.example.chidori.proxytestapp.Utils.Beans.CollectionListBean;
import com.example.chidori.proxytestapp.Utils.Beans.CreateCollectionBean;
import com.example.chidori.proxytestapp.Utils.Beans.DeleteSourceBean;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.ModifyCollectionBean;
import com.example.chidori.proxytestapp.Utils.Beans.RecommendListBean;
import com.example.chidori.proxytestapp.Utils.Beans.RefreshSourceBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;


import com.example.chidori.proxytestapp.Utils.Beans.SaveEntryBean;
import com.example.chidori.proxytestapp.Utils.Beans.SaveRSSBean;
import com.example.chidori.proxytestapp.Utils.Beans.SourceListBean;
import com.example.chidori.proxytestapp.Utils.Beans.UpdateBean;
import com.example.chidori.proxytestapp.Utils.Beans.UpdateCollectionBean;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {

    //登录
    @Headers({
            "Accept: application/json",
    })
    @POST("user/login")
    Observable<LoginBean> requestLogin(@Body JsonObject jsonObject);

    //注册
    @Headers({
            "Accept: application/json",
    })
    @POST("user/register")
    Observable<RegisterBean> requestRegister(@Body JsonObject jsonObject);

    //更新
    @Headers({
            "Accept: application/json",
    })
    @POST("user/update")
    Observable<UpdateBean> requestUpdate(@Body JsonObject jsonObject);

    //创建
    @Headers({
            "Accept: application/json",
    })
    @POST("collection")
    Observable<CreateCollectionBean> createCollection(@Body JsonObject jsonObject);

    //更新收藏夹
    @Headers({
            "Accept: application/json",
    })
    @POST("collection/update")
    Observable<UpdateCollectionBean> updateCollection(@Body JsonObject jsonObject);

    //列出文章
    @Headers({
            "Accept: application/json",
    })
    @GET("entry/list/publicToAll")
    Observable<EntryListBean> getPublicEntriesToAll(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @GET("entry/list/publicToGroup")
    Observable<EntryListBean> getPublicEntriesToGroup(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @POST("collection/changeStatus")
    Observable<ModifyCollectionBean> changeCollectionStatus(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @POST("collection/delete")
    Observable<ModifyCollectionBean> deleteCollection(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @POST("collection/clear")
    Observable<ModifyCollectionBean> clearCollection(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @POST("collection/addEntry")
    Observable<ModifyCollectionBean> addEntryToCollection(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @POST("collection/moveEntry")
    Observable<ModifyCollectionBean> moveEntry(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @POST("source/other")
    Observable<AddSourceBean> addOtherSource(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @GET("collection/list")
    Observable<CollectionListBean> getCollectionList(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @GET("collection/list/allPublic")
    Observable<CollectionListBean> getAllPublic(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @GET("collection/list/groupPublic")
    Observable<CollectionListBean> getGroupPublic(@Body JsonObject jsonObject);



    //通过来源获取所有文章
    @Headers({
            "Accept: application/json",
    })
    @GET("entry/list/source")
    Observable<EntryListBean> getEntriesBySource(@Body JsonObject jsonObject);

    //收藏夹下全部文章
    @Headers({
            "Accept: application/json",
    })
    @GET("entry/list/source")
    Observable<EntryListBean> getEntriesOfCollection(@Body JsonObject jsonObject);


    //保存文章（通过链接）
    @Headers({
            "Accept: application/json",
    })
    @POST("source/other")
    Observable<SaveEntryBean> saveEntryFromLink(@Body JsonObject jsonObject);


    //RSS部分

    //获取预置的RSS源
    /*
    @Headers({
            "Accept: application/json",
    })
    @GET("source/list/recommend")
    Observable<RecommendListBean> getRecommendList(@Body JsonObject jsonObject);*/

    //通过链接存储单个RSS源
    //保存文章（通过链接）
    @Headers({
            "Accept: application/json",
    })
    @POST("source/rss/single/link")
    Observable<SaveRSSBean> saveRSSLink(@Body JsonObject jsonObject);


    /**
     * 获取某一type的源
     * 0: RSS
     * 1: Other
     */
    @Headers({
            "Accept: application/json",
    })
    @GET("source/list")
    Observable<SourceListBean> getSources(@Body JsonObject jsonObject);


    //刷新单个RSS源
    @Headers({
            "Accept: application/json",
    })
    @POST("source/rss/single/refresh")
    Observable<RefreshSourceBean> refreshSingleRSS(@Body JsonObject jsonObject);


    //刷新全部RSS源
    @Headers({
            "Accept: application/json",
    })
    @POST("source/rss/batch/refresh")
    Observable<RefreshSourceBean> refreshAllRSS(@Body JsonObject jsonObject);

    //删除RSS源
    @Headers({
            "Accept: application/json",
    })
    @POST("source/rss/single/refresh")
    Observable<DeleteSourceBean> deleteRSS(@Body JsonObject jsonObject);

    //userid = 061583de-12be-4116-ac58-e7343aa7f024
    //collectionid = a6c04078-7da9-44e3-8817-0fb216cf830a
    //entryid = ""2da32695-2bf5-4308-b5ef-9a26e6f6c941""
    //空的 28084a85-6d10-4693-bd27-d031b2478bcf
    //sourceid = "586104ba-81a7-4682-89cd-7a6dfc09826d"
}
