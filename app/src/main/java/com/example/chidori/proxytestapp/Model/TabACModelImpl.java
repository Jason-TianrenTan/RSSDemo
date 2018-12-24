package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.CollectionListBean;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class TabACModelImpl implements Contract.ITabACModel {

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public ArrayList<Collection> getCollections() {
        return collections;
    }

    private ArrayList<Entry> entries;
    private ArrayList<Collection> collections;
    @Override
    public void doGetGroupEntries(String groupId) {
        new UniversalPresenter().GetEntryListPublicToGroup("all");
    }

    @Override
    public void doGetGroupCollections(String groupId) {
        new UniversalPresenter().GetGroupPublicCollections(groupId);
    }

    @Override
    public void doAddEntry(String collectionId, String entryId) {
        new UniversalPresenter().AddEntryToCollection(collectionId, entryId);
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

    public void setupCollections(CollectionListBean.ResResultBean bean) {
        collections = new ArrayList<>();
        ArrayList<CollectionListBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(bean.getCurData());
        for (CollectionListBean.ResResultBean.CurDataBean curData : curDatas) {
            collections.add(new Collection(curData.getCollectionId(), curData.getName(), curData.getDescription(), curData.getPublicStatus(),
                    curData.getCreateTime(), curData.getUpdateTime()));
        }
    }
}
