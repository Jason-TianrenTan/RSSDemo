package com.example.chidori.proxytestapp.Model;


import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.URLEvent;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReaderModelImpl implements Contract.IReaderModel {

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    private String nickname = "网页";
    private String titleName = "来自网页";

    @Override
    public void doLoadURL(String url) {
        new Thread(()-> {
            Request request = new Request.Builder().url(url).build();
            Response response = null;
            String body = "";
            Document document = null;
            OkHttpClient client = new OkHttpClient.Builder().build();
            try {
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    body =  response.body().string();
                    document = Jsoup.parse(body);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (document != null) {
                if (url.contains("blog.csdn.net")) {//CSDN
                    nickname = "CSDN";
                } else if (url.contains("www.jianshu.com")) {
                    //简书
                    nickname = "简书";
                } else if (url.contains("www.cnblogs.com")) {
                    nickname = "博客园";
                } else if (url.contains("daily.zhihu.com")) {
                    nickname = "知乎日报";
                }
                EventBus.getDefault().post(new URLEvent(nickname));
            }
        }).start();

    }
}
