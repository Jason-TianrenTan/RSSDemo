package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class EntryBySourceActivityModelImpl implements Contract.IEntryBySourceActivityModel {

    private ArrayList<Entry> entries;

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

    @Override
    public void doGetEntriesBySource(String sourceId) {
        new UniversalPresenter().GetEntryListBySource(sourceId);
    }

    @Override
    public void doAddEntryToCollection(String collectionId, String entryId) {
        new UniversalPresenter().AddEntryToCollection(collectionId, entryId);
    }
}
