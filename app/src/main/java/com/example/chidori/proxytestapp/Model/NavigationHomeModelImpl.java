package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.Beans.SourceListBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class NavigationHomeModelImpl implements Contract.INavigationHomeModel {

    public ArrayList<Source> getSources() {
        return sources;
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    private ArrayList<Source> sources;
    private ArrayList<Entry> entries;
    @Override
    public void doGetSources(int type) {
        new UniversalPresenter().GetSources(type, Config.userId);
    }

    @Override
    public void deleteSource(String sourceId) {

    }

    @Override
    public void doGetPublicEntries() {
        new UniversalPresenter().GetEntryListPublicToAll("all");
    }

    @Override
    public void doAddEntry(String collectionId, String entryId) {
        new UniversalPresenter().AddEntryToCollection(collectionId, entryId);
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
}
