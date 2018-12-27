package com.example.chidori.proxytestapp.Activities.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Activities.entity.GroupMember;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StaticTool {
    public static List<String> myEntryIdList = new ArrayList<>();

    public static List<Collection> collectionCardList = new ArrayList<>();
    public static List<Source> sourceCardList = new ArrayList<>();

    public static int opPosition = -1;
    public static String opString;

    public static List<Entry> getTestEntryCardList(){
        List<Entry> entryCardList = new ArrayList<>();
        for(int i=0;i<10;i++) {
            entryCardList.add(new Entry("id"+i, "title"+i, "", "", "", "", "来源name", "", "",""));
        }
        return entryCardList;
    }

    public static List<Collection> getTestCollectionCardList(){
        List<Collection> starCardList = new ArrayList<>();
        for(int i=0;i<8;i++) starCardList.add(new Collection(UUID.randomUUID().toString(),"收藏夹"+i,"",2,"",""));
        return starCardList;
    }

    public static List<Group> getTestGroupCardList(){
        List<Group> groupCardList = new ArrayList<>();
        for(int i=0;i<7;i++) groupCardList.add(new Group(UUID.randomUUID().toString(),"小组"+i,"","","","",""));
        return groupCardList;
    }

    public static List<GroupMember> getTestUserCardList(){
        List<GroupMember> memberList = new ArrayList<>();
        for(int i=0;i<6;i++) memberList.add(new GroupMember(UUID.randomUUID().toString(),"用户"+i,0,"","",""));
        return memberList;
    }

    public static List<Source> getTestSourceCardList(){
        List<Source> memberList = new ArrayList<>();
        for(int i=0;i<5;i++) memberList.add(new Source(UUID.randomUUID().toString(),"来源"+i,"","",0,"",""));
        return memberList;
    }

}
