package com.example.chidori.proxytestapp.Model;

import android.content.SharedPreferences;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.CollectionListBean;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class MenuModelImpl implements Contract.IMenuModel {
    @Override
    public void doAddRSSFromLink(String link) {
        new UniversalPresenter().SaveSingleRSS(link, Config.userId);
    }

    private ArrayList<Collection> collections;

    public ArrayList<Collection> getCollections() {
        return collections;
    }

    public void setCollections(ArrayList<Collection> collections) {
        this.collections = collections;
    }

    public void setupCollections(CollectionListBean.ResResultBean bean) {
        collections = new ArrayList<>();
        ArrayList<CollectionListBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(bean.getCurData());
        for (CollectionListBean.ResResultBean.CurDataBean curData : curDatas) {
            collections.add(new Collection(curData.getCollectionId(), curData.getName(),
                    curData.getDescription(), curData.getPublicStatus(),
                    curData.getCreateTime(), curData.getUpdateTime()));
        }
    }

    @Override
    public void doGetUserCollections() {
        new UniversalPresenter().GetCollections(-1, Config.userId);
    }

    public void setupEntries(EntryListBean.ResResultBean bean) {
        entries = new ArrayList<>();
        ArrayList<EntryListBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(bean.getCurData());
        for (EntryListBean.ResResultBean.CurDataBean curData : curDatas) {
            entries.add(new Entry(curData.getEntryId(), curData.getTitle(), curData.getDescription(),
                    curData.getEntryLink(), curData.getCollectionId(), curData.getSourceId(),
                    curData.getSourceName(), curData.getSourceLink(), curData.getCollectionName(), curData.getUpdateTime()));
        }
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    private ArrayList<Entry> entries;

    @Override
    public void doGetEntriesByCollection(String collectionId) {
        new UniversalPresenter().GetEntryOfCollection(collectionId);
    }
}
