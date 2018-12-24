package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.CollectionListBean;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.Beans.GroupMemberBean;
import com.example.chidori.proxytestapp.Utils.Beans.SourceListBean;
import com.example.chidori.proxytestapp.Utils.Beans.UserGroupsBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class ListModelImpl implements Contract.IListModel {

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

    public void setupSources(SourceListBean.ResResultBean bean) {
        sources = new ArrayList<>();
        ArrayList<SourceListBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(bean.getCurData());
        for (SourceListBean.ResResultBean.CurDataBean curData : curDatas) {
            sources.add(new Source(curData.getSourceId(), curData.getName(),
                    curData.getDescription(), curData.getLink(), curData.getType(),
                    curData.getCreateTime(), curData.getUpdateTime()));
        }
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

    public void setupUserGroups(UserGroupsBean.ResResultBean bean) {
        userGroups = new ArrayList<>();
        ArrayList<UserGroupsBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(bean.getCurData());
        for (UserGroupsBean.ResResultBean.CurDataBean curData : curDatas) {
            userGroups.add(new Group(curData.getGroupId(), curData.getName(), curData.getGroupNum(),
                    curData.getDescription(), curData.getUserId(), curData.getCreateTime(), curData.getUpdateTime()));
        }
    }

    private ArrayList<Collection> collections;

    public ArrayList<Group> getUserGroups() {
        return userGroups;
    }

    private ArrayList<Group> userGroups;

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    private ArrayList<Entry> entries;


    public ArrayList<Source> getSources() {
        return sources;
    }

    public void setSources(ArrayList<Source> sources) {
        this.sources = sources;
    }

    private ArrayList<Source> sources;


    @Override
    public void doGetUserCollections() {
        new UniversalPresenter().GetCollections(-1, Config.userId);
    }

    @Override
    public void deleteCollection(String collectionId) {
        new UniversalPresenter().DeleteCollection(collectionId);
    }

    @Override
    public void doGetUserSources() {
        new UniversalPresenter().GetSources(-1, Config.userId);
    }

    @Override
    public void deleteSource(String sourceId) {
        new UniversalPresenter().DeleteRSS(sourceId);
    }

    @Override
    public void doGetEntriesBySource(String sourceId) {
        new UniversalPresenter().GetEntryListBySource(sourceId);
    }

    @Override
    public void doGetEntriesByCollection(String collectionId) {
        new UniversalPresenter().GetEntryOfCollection(collectionId);
    }

    @Override
    public void doAddEntryToCollection(String collectionId, String entryId) {
        new UniversalPresenter().AddEntryToCollection(collectionId, entryId);
    }

    @Override
    public void doCreateCollection(String name, String desc, int publicStatus) {
        new UniversalPresenter().CreateCollection(name, desc, publicStatus, Config.userId);
    }

    @Override
    public void doGetUserGroups(String userId) {
        new UniversalPresenter().GetUserGroups(userId);
    }

}
