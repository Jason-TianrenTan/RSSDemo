package com.example.chidori.proxytestapp.Model;

import android.content.SharedPreferences;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.CollectionListBean;
import com.example.chidori.proxytestapp.Utils.Beans.EntryListBean;
import com.example.chidori.proxytestapp.Utils.Beans.SaveRSSBean;
import com.example.chidori.proxytestapp.Utils.Beans.SourceListBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class MenuModelImpl implements Contract.IMenuModel {

    private ArrayList<Collection> collections;

    public void setupCollections(CollectionListBean.ResResultBean bean) {
        collections = new ArrayList<>();
        ArrayList<CollectionListBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(bean.getCurData());
        for (CollectionListBean.ResResultBean.CurDataBean curData : curDatas) {
            collections.add(new Collection(curData.getCollectionId(), curData.getName(),
                    curData.getDescription(), curData.getPublicStatus(),
                    curData.getCreateTime(), curData.getUpdateTime()));
        }
    }

    public ArrayList<Collection> getCollections() {
        return collections;
    }

    @Override
    public void doAddRSSFromLink(String link) {
        new UniversalPresenter().SaveSingleRSS(link, Config.userId);
    }

    @Override
    public void doGetUserCollections() {
        new UniversalPresenter().GetCollections(-1, Config.userId);
    }
}
