package com.example.chidori.proxytestapp.Utils.IO;

import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("login")//登录
    Observable<LoginBean> requestLogin(@Field("username") String username, @Field("password") String password);
}
