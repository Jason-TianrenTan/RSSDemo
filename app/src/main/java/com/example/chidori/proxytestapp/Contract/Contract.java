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
        void onEntryAdded(String status);
    }

    interface IReaderModel {
        void doLoadURL(String url);
        void doAddToCollection(String link, String collectionId, String description, String title);
    }

    interface IReaderPresenter {
        void doLoadURL(String url);
        String getTitle();
        String getNickname();
        void setTitle(String title);
        void doAddToCollection(String link, String collectionId, String description, String title);
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
    }

    interface IMenuModel {
        void doAddRSSFromLink(String link);
        void doGetUserCollections();
    }

    interface IMenuPresenter {
        void doAddRSSFromLink(String link);
        void doGetUserCollections();
    }

//    interface IListView {
//        void onEntryRemoved(String status);
//        void onUserInfoResult(GroupMember member);
//    }
//    interface IListModel {
//        void removeEntry(String entryId);
//        void doGetUserInfo(String userId);
//    }
//    interface IListPresenter {
//        void removeEntry(String entryId);
//        void doGetUserInfo(String userId);
//    }

    interface IEntryByCollectionActivityView {
        void onEntriesByCollectionRetrieved(String status);
        void onEntryAdded(String status);
    }

    interface IEntryByCollectionActivityModel{
        void doGetEntriesByCollection(String collectionId);
        void doAddEntryToCollection(String collectionId, String entryId);
    }

    interface IEntryByCollectionActivityPresenter {
        void doGetEntriesByCollection(String collectionId);
        void doAddEntryToCollection(String collectionId, String entryId);
    }

    interface IEntryBySourceActivityView {
        void onEntriesBySourceRetrieved(String status);
        void onEntryAdded(String status);
    }

    interface IEntryBySourceActivityModel {
        void doGetEntriesBySource(String sourceId);
        void doAddEntryToCollection(String collectionId, String entryId);
    }

    interface IEntryBySourceActivityPresenter {
        void doGetEntriesBySource(String sourceId);
        void doAddEntryToCollection(String collectionId, String entryId);
    }

    interface ICollectionView {
        void onCollectionDeleted(String status);
        void onCollectionCreated(String status);
    }

    interface ICollectionModel {
        void deleteCollection(String collectionId);
        void doCreateCollection(String name, String desc, int publicStatus);
    }

    interface ICollectionPresenter {
        void deleteCollection(String collectionId);
        void doCreateCollection(String name, String desc, int publicStatus);
    }

    interface ISourceView {
        void onLinkResult(SaveRSSBean.ResResultBean bean);
        void onSourceDeleted(String status);
    }

    interface ISourceModel {
        void doAddRSSFromLink(String link);
        void deleteSource(String sourceId);
    }

    interface ISourcePresenter {
        void deleteSource(String sourceId);
        void doAddRSSFromLink(String link);
    }

    interface IGroupView {
        void onUserGroupsRetrieved(String status);
        void onGroupCreated(String status);
    }

    interface IGroupModel {
        void doCreateGroup(String groupName, String desc);
        void doGetUserGroups();
    }

    interface IGroupPresenter {
        void doCreateGroup(String groupName, String desc);
        void doGetUserGroups();
    }

    interface IGroupDetailView {
        void onGroupEntered(String status);
        void onGroupQuit(String status);
        void onGroupMembersResult(String status);
    }

    interface IGroupDetailModel {
        void doEnterGroup(String groupId);
        void doQuitGroup(String groupId);
        void doGetGroupMembers(String groupId);
    }

    interface IGroupDetailPresenter {
        void doEnterGroup(String groupId);
        void doQuitGroup(String groupId);
        void doGetGroupMembers(String groupId);
    }

    interface IPublicEntryFragmentView {
        void onPublicEntriesRetrieved(String status);
        void onEntryAdded(String status);
    }

    interface IPublicEntryFragmentModel {
        void doGetPublicEntries();
        void doAddEntryToCollection(String collectionId, String entryId);
    }

    interface IPublicEntryFragmentPresenter {
        void doGetPublicEntries();//获取全部公开的文章(手机)response1
        void doAddEntryToCollection(String collectionId, String entryId);//添加文章到收藏夹
    }

    interface ISourceFragmentView {
        void onSourceDeleted(String status);
        void onUserSourcesRetrieved(String status);
    }

    interface ISourceFragmentModel {
        void deleteSource(String sourceId);
        void doGetUserSources();
    }

    interface ISourceFragmentPresenter {
        void deleteSource(String sourceId);//根据sourceId删除source

        void doGetUserSources();
    }

    interface IGroupEntryFragmentView {
        void onGroupEntriesRetrieved(String status);
        void onEntryAdded(String status);
    }

    interface IGroupEntryFragmentModel {
        void doGetGroupEntries(String groupId);//小组内公开
        void doAddEntry(String collectionId, String entryId);//收藏
    }

    interface IGroupEntryFragmentPresenter {
        void doGetGroupEntries(String groupId);//小组内公开
        void doAddEntryToCollection(String collectionId, String entryId);//收藏
    }

    interface IGroupCollectionFragmentView {
        void onGroupCollectionsRetrieved(String status);
    }

    interface IGroupCollectionFragmentModel {
        void doGetGroupCollections(String groupId);//小组内收藏夹
    }

    interface IGroupCollectionFragmentPresenter {
        void doGetGroupCollections(String groupId);//小组内收藏夹
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

}
