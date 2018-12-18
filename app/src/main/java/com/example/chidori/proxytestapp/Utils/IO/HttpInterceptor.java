package com.example.chidori.proxytestapp.Utils.IO;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        System.out.println("Request chain : " + request.url().toString());
        Response response = chain.proceed(request);
        return response;
    }
}