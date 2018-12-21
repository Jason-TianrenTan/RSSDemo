package com.example.chidori.proxytestapp.Utils.IO;

import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
}
