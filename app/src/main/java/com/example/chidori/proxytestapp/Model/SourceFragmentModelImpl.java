package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.SourceListBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;


public class SourceFragmentModelImpl implements Contract.ISourceFragmentModel {

    private ArrayList<Source> sources;

    public void setupSources(SourceListBean.ResResultBean bean) {
        sources = new ArrayList<>();
        ArrayList<SourceListBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(bean.getCurData());
        for (SourceListBean.ResResultBean.CurDataBean curData : curDatas) {
            sources.add(new Source(curData.getSourceId(), curData.getName(),
                    curData.getDescription(), curData.getLink(), curData.getType(),
                    curData.getCreateTime(), curData.getUpdateTime()));
        }
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    @Override
    public void deleteSource(String sourceId) {

    }

    @Override
    public void doGetUserSources() {
        new UniversalPresenter().GetSources(-1, Config.userId);
    }
}
