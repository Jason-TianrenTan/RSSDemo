package com.example.chidori.proxytestapp.Model;

import com.example.chidori.proxytestapp.Activities.entity.GroupMember;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Utils.Beans.GroupMemberBean;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import java.util.ArrayList;

public class GroupDetailModelImpl implements Contract.IGroupDetailModel {

    private ArrayList<GroupMember> members;

    public void setupMembers(GroupMemberBean.ResResultBean result) {
        members = new ArrayList<>();
        ArrayList<GroupMemberBean.ResResultBean.CurDataBean> curDatas = new ArrayList<>(result.getCurData());
        for (GroupMemberBean.ResResultBean.CurDataBean curData : curDatas) {
            members.add(new GroupMember(curData.getUserId(), curData.getUsername(), curData.getSex(), curData.getPhone(),
                    curData.getEmail(), curData.getAvatar()));
        }
    }

    public ArrayList<GroupMember> getMembers() {
        return members;
    }

    @Override
    public void doEnterGroup(String groupId) {
        new UniversalPresenter().EnterGroup(groupId, Config.userId);
    }

    @Override
    public void doQuitGroup(String groupId) {
        new UniversalPresenter().QuitGroup(groupId, Config.userId);
    }

    @Override
    public void doGetGroupMembers(String groupId) {
        new UniversalPresenter().GetGroupMembers(groupId);
    }
}
