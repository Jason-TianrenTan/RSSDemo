package com.example.chidori.proxytestapp.Contract;

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
    }

    interface IMenuModel {
        void doAddRSSFromLink(String link);
    }

    interface IMenuPresenter {
        void doAddRSSFromLink(String link);
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
    }

    interface IListModel {
        void doGetUserCollections();
        void deleteCollection(String collectionId);
        void doGetUserSources();
        void deleteSource(String sourceId);
        void doGetEntriesBySource(String sourceId);
        void doGetEntriesByCollection(String collectionId);
        void doAddEntryToCollection(String collectionId, String entryId);
    }

    interface IListPresenter {
        void doGetUserCollections();
        void deleteCollection(String collectionId);
        void doGetUserSources();
        void deleteSource(String sourceId);
        void doGetEntriesBySource(String sourceId);
        void doGetEntriesByCollection(String collectionId);
        void doAddEntryToCollection(String collectionId, String entryId);
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
        void doQuitGroup(String status);
    }
}
