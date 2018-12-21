package com.example.chidori.proxytestapp.Activities.util;

import com.example.chidori.proxytestapp.Activities.entity.IntroCard;
import com.example.chidori.proxytestapp.Activities.entity.StarCard;

import java.util.ArrayList;
import java.util.List;

public class staticData {
    public static final int WDDR = 0;   //我的导入
    public static final int QBGX = 1;   //全部更新
    public static final int SCJ = 2;    //收藏夹
    public static List<String> starList = getTestStarList();
    public static List<IntroCard> WDDRList = getTestIntroCardList();
    public static List<StarCard> SCJList = getTestStarCardList();

    public static List<IntroCard> getTestIntroCardList(){
        List<IntroCard> introCardList = new ArrayList<>();
        for(int i=0;i<10;i++) {
            introCardList.add(new IntroCard("id"+i,"title"+i,"detail"+i));
        }
        return introCardList;
    }

    public static List<String> getTestStarList() {
        starList = new ArrayList<>();
        starList.add("id1");
        starList.add("id3");
        return starList;
    }

    public static List<StarCard> getTestStarCardList(){
        List<StarCard> starCardList = new ArrayList<>();
        for(int i=0;i<5;i++) starCardList.add(new StarCard("收藏夹"+i));
        return starCardList;
    }
}
