package com.example.chidori.proxytestapp.Model;


import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.URLEvent;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

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

    private String link = "";

    public String getLink() {
        return link;
    }
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
                link = url;
                if (url.contains("csdn")) {//CSDN
                    nickname = "CSDN";
                } else if (url.contains("jianshu")) {
                    //简书
                    nickname = "简书";
                } else if (url.contains("cnblogs")) {
                    nickname = "博客园";
                } else if (url.contains("zhihu")) {
                    nickname = "知乎";
                } else if (url.contains("iteye")) {
                    nickname = "ITeye";
                }
                EventBus.getDefault().post(new URLEvent(nickname));
            }
        }).start();

    }

    @Override
    public void doAddToCollection(String link, String collectionId, String description, String title) {
        new UniversalPresenter().SaveEntryFromLink(link, collectionId, description, title);
    }
}
