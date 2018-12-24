package com.example.chidori.proxytestapp.Contract;

import com.example.chidori.proxytestapp.Activities.entity.GroupMember;
import com.example.chidori.proxytestapp.Events.SaveRSSEvent;
import com.example.chidori.proxytestapp.Events.UpdateAccountEvent;
import com.example.chidori.proxytestapp.Utils.Beans.CollectionListBean;
import com.example.chidori.proxytestapp.Utils.Beans.LoginBean;
import com.example.chidori.proxytestapp.Utils.Beans.RegisterBean;
import com.example.chidori.proxytestapp.Utils.Beans.SaveRSSBean;
import com.example.chidori.proxytestapp.Utils.Beans.UpdateBean;

public interface Contract {


    //阅读器
    interface IReaderView {
        void onTitleFound(String title);
    }

    interface IReaderModel {
        void doLoadURL(String url);
    }

    interface IReaderPresenter {
        void doLoadURL(String url);
        String getTitle();
        String getNickname();
        void setTitle(String title);
    }


    //注册
    interface IRegisterView {
        void onRegisterResult(RegisterBean.ResResultBean resResultBean);
    }

    interface IRegisterModel {
        void doRegister(String username, String password, String phone, String email, int sex);
    }

    interface IRegisterPresenter {
        void doRegister(String username, String password, String phone, String email, int sex);
    }


    interface ILoginView {
        void onLoginCall(LoginBean.ResResultBean bean);
    }

    interface ILoginModel {

        void doLogin(String username, String password);
    }

    interface ILoginPresenter {
        void doLogin(String username, String password);
    }

    //更新用户信息
    interface IUpdateView {
        void onUpdateResult(UpdateBean.ResResultBean bean);
    }

    interface IUpdateModel {
        void doUpdateUserInfo(String username, String password, String phone, String email, int sex, String userId);
    }

    interface IUpdatePresenter {

    }


    interface IMenuView {
        void onLinkResult(SaveRSSBean.ResResultBean bean);
        void onUserCollectionsCall(String status);
        void onEntriesByCollectionRetrieved(String success);
    }

    interface IMenuModel {
        void doAddRSSFromLink(String link);
        void doGetUserCollections();

        void doGetEntriesByCollection(String collectionId);
    }

    interface IMenuPresenter {
        void doAddRSSFromLink(String link);
        void doGetUserCollections();

        void doGetEntriesByCollection(String collectionId);
    }


    interface IFragmentsView {
        void onEntriesResult();
    }

    interface IFramentsModel {

    }

    interface IFragmentsPresenter {

    }


    interface IListView {
        void onUserCollectionsCall(String status);
        void onCollectionDeleted(String status);
        void onUserSourcesRetrieved(String status);
        void onSourceDeleted(String status);
        void onEntriesBySourceRetrieved(String status);
        void onEntriesByCollectionRetrieved(String status);
        void onEntryAddedToCollection(String status);
        void onCollectionCreated(String status);
        void onUserGroupsRetrieved(String status);
    }

    interface IListModel {
        void doGetUserCollections();
        void deleteCollection(String collectionId);
        void doGetUserSources();
        void deleteSource(String sourceId);
        void doGetEntriesBySource(String sourceId);
        void doGetEntriesByCollection(String collectionId);
        void doAddEntryToCollection(String collectionId, String entryId);
        void doCreateCollection(String name, String desc, int publicStatus);
        void doGetUserGroups(String userId);
    }

    interface IListPresenter {
        void doGetUserCollections();
        void deleteCollection(String collectionId);
        void doGetUserSources();
        void deleteSource(String sourceId);
        void doGetEntriesBySource(String sourceId);
        void doGetEntriesByCollection(String collectionId);
        void doAddEntryToCollection(String collectionId, String entryId);
        void doCreateCollection(String name, String desc, int publicStatus);
        void doGetUserGroups(String userId);
    }


    interface IGroupView {
        void onGroupCreated(String status);
    }

    interface IGroupModel {
        void doCreateGroup(String groupName, String desc);
    }

    interface IGroupPresenter {
        void doCreateGroup(String groupName, String desc);
    }


    interface IGroupDetailView {
        void onGroupEntered(String status);
        void onGroupQuit(String status);
    }

    interface IGroupDetailModel {
        void doEnterGroup(String groupId);
        void doQuitGroup(String groupId);
    }

    interface IGroupDetailPresenter {
        void doEnterGroup(String groupId);
        void doQuitGroup(String groupId);
    }


    //TabFragment：
    //属于NavigationFragment的home界面
    interface INavigationHomeView {
        void onSourceGet(String status);
        void onSourceDeleted(String status);
        void onPublicEntriesRetrieved(String status);
        void onEntryAdded(String status);//给收藏夹添加文章
    }

    interface INavigationHomeModel {
        void doGetSources(int type);
        void deleteSource(String sourceId);
        void doGetPublicEntries();
        void doAddEntry(String collectionId, String entryId);
    }

    interface INavigationHomePresenter {
        void doGetSources(int type);//获取某一用户某一(0,1)类型的来源
        void deleteSource(String sourceId);//根据sourceId删除source
        void doGetPublicEntries();//获取全部公开的文章(手机)response1
        void doAddEntry(String collectionId, String entryId);//添加文章到收藏夹
    }


    //属于TabActivity
    interface ITabACView {
        void onGroupCollectionsRetrieved(String status);//小组内收藏夹
        void onGroupEntriesRetrieved(String status);
        void onEntryAdded(String status);//给收藏夹添加文章
    }

    interface ITabACModel {
        void doGetGroupEntries(String groupId);//小组内公开
        void doGetGroupCollections(String groupId);//小组内收藏夹
        void doAddEntry(String collectionId, String entryId);//收藏
    }

    interface ITabACPresenter {
        void doGetGroupEntries(String groupId);//小组内公开
        void doGetGroupCollections(String groupId);//小组内收藏夹
        void doAddEntry(String collectionId, String entryId);//收藏
    }


    //NavigationFragment.java.java (属于MenuActivity)
    interface INavMenuView {
        void onGroupSearchResult(String status);
    }

    interface INavMenuModel {
        void doSearchGroup(String keyword);
    }

    interface INavMenuPresenter {
        void doSearchGroup(String keyword);
    }


    //TabActivity
    interface ITabView {
        void onGroupMembersResult(String status);
        void onUserInfoResult(GroupMember member);
    }

    interface ITabModel {
        void doGetGroupMembers(String groupId);
        void doGetUserInfo(String userId);
    }

    interface ITabPresenter {
        void doGetGroupMembers(String groupId);
        void doGetUserInfo(String userId);//根据UserId获取成员信息
    }

}
