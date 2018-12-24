package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.GroupMember;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.GroupMemberBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class TabModelImpl implements Contract.ITabModel {

    public ArrayList<GroupMember> getMembers() {
        return members;
    }

    private ArrayList<GroupMember> members;
    @Override
    public void doGetGroupMembers(String groupId) {
        new UniversalPresenter().GetGroupMembers(groupId);
    }

    @Override
    public void doGetUserInfo(String userId) {
        new UniversalPresenter().QueryUserInfo(userId);
    }

    public void setupMembers(GroupMemberBean.ResResultBean result) {
        members = new ArrayList<>();
        ArrayList<GroupMemberBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(result.getCurData());
        for (GroupMemberBean.ResResultBean.CurDataBean curData : curDatas) {
            members.add(new GroupMember(curData.getUserId(), curData.getUsername(), curData.getSex(), curData.getPhone(),
                    curData.getEmail(), curData.getAvatar()));
        }
    }
}
