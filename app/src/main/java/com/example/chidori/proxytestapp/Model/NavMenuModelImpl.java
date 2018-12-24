package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.SearchGroupBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class NavMenuModelImpl implements Contract.INavMenuModel {

    public ArrayList<Group> getGroups() {
        return groups;
    }

    ArrayList<Group> groups;


    @Override
    public void doSearchGroup(String keyword) {
        new UniversalPresenter().SearchGroup(keyword);
    }

    public void setupGroups(SearchGroupBean.ResResultBean result) {
        groups = new ArrayList<>();
        ArrayList<SearchGroupBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(result.getCurData());
        for (SearchGroupBean.ResResultBean.CurDataBean curData : curDatas) {
            groups.add(new Group(curData.getGroupId(), curData.getName(), curData.getGroupNum(),
                    curData.getDescription(), curData.getUserId(), curData.getCreateTime(), curData.getUpdateTime()));
        }
    }

}
