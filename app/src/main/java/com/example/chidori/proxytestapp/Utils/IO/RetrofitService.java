package com.example.chidori.proxytestapp.Utils.IO;

import com.example.chidori.proxytestapp.Utils.Beans.CreateCollectionBean;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;
import com.example.chidori.proxytestapp.Utils.Beans.URLBean;


import com.example.chidori.proxytestapp.Utils.Beans.UpdateBean;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    //列出文章
    @Headers({
            "Accept: application/json",
    })
    @POST("entry/list/publicToAll")
    Observable<EntryListBean> getPublicEntriesToAll(@Body JsonObject jsonObject);

    @Headers({
            "Accept: application/json",
    })
    @POST("entry/list/publicToGroup")
    Observable<EntryListBean> getPublicEntriesToGroup(@Body JsonObject jsonObject);
}
