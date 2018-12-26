package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.CollectionListBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class GroupCollectionFragmentModelImpl implements Contract.IGroupCollectionFragmentModel{

    private ArrayList<Collection> collections;

    public void setupCollections(CollectionListBean.ResResultBean bean) {
        collections = new ArrayList<>();
        ArrayList<CollectionListBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(bean.getCurData());
        for (CollectionListBean.ResResultBean.CurDataBean curData : curDatas) {
            collections.add(new Collection(curData.getCollectionId(), curData.getName(), curData.getDescription(), curData.getPublicStatus(),
                    curData.getCreateTime(), curData.getUpdateTime()));
        }
    }

    public ArrayList<Collection> getCollections() {
        return collections;
    }

    @Override
    public void doGetGroupCollections(String groupId) {
        new UniversalPresenter().GetGroupPublicCollections(groupId);
    }
}
