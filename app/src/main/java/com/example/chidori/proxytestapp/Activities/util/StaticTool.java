package com.example.chidori.proxytestapp.Activities.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chidori.proxytestapp.Activities.entity.GroupCard;
import com.example.chidori.proxytestapp.Activities.entity.EntryCard;
import com.example.chidori.proxytestapp.Activities.entity.SourceCard;
import com.example.chidori.proxytestapp.Activities.entity.UserCard;
import com.example.chidori.proxytestapp.Activities.entity.CollectionCard;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;

public class StaticTool {
    public static List<String> starList = getTestStarList();
    public static List<CollectionCard> collectionCardList = getTestCollectionCardList();
    public static List<GroupCard> groupCardList = getTestGroupCardList();
    public static List<SourceCard> sourceCardList = getTestSourceCardList();

    public static List<EntryCard> getTestEntryCardList(){
        List<EntryCard> entryCardList = new ArrayList<>();
        for(int i=0;i<10;i++) {
            entryCardList.add(new EntryCard("id"+i,"title"+i,"detail"+i));
        }
        return entryCardList;
    }

    public static List<String> getTestStarList() {
        starList = new ArrayList<>();
        starList.add("id1");
        starList.add("id3");
        return starList;
    }

    public static List<CollectionCard> getTestCollectionCardList(){
        List<CollectionCard> starCardList = new ArrayList<>();
        for(int i=0;i<8;i++) starCardList.add(new CollectionCard("收藏夹id"+i,"收藏夹"+i,2));
        return starCardList;
    }

    public static List<GroupCard> getTestGroupCardList(){
        List<GroupCard> groupCardList = new ArrayList<>();
        for(int i=0;i<7;i++) groupCardList.add(new GroupCard("小组id"+i,"小组"+i));
        return groupCardList;
    }

    public static List<UserCard> getTestUserCardList(){
        List<UserCard> memberList = new ArrayList<>();
        for(int i=0;i<6;i++) memberList.add(new UserCard("用户id"+i,"用户"+i));
        return memberList;
    }

    public static List<SourceCard> getTestSourceCardList(){
        List<SourceCard> memberList = new ArrayList<>();
        for(int i=0;i<5;i++) memberList.add(new SourceCard("来源id"+i,"来源"+i));
        return memberList;
    }

    public static void setSourceCardRecyclerView(RecyclerView.Adapter recyclerAdapter, View view){
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerAdapter);
    }
}
