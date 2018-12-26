//package com.example.chidori.proxytestapp.Presenter;
//
//import com.example.chidori.proxytestapp.Contract.Contract;
//import com.example.chidori.proxytestapp.Model.ListModelImpl;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.ArrayList;
//
//public class ListPresenterImpl implements Contract.IListPresenter {
//
//    public ListPresenterImpl() {
//        model = new ListModelImpl();
//        if (!EventBus.getDefault().isRegistered(this))
//            EventBus.getDefault().register(this);
//    }
//
//    private ListModelImpl model;
//
//    private Contract.IListView listView;
//
//    public void attachView(Contract.IListView view) {
//        listView = view;
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onUserDetailRetrieved(UserDetailEvent userDetailEvent) {
//        GroupMember userInfo = null;
//        if (userDetailEvent.getResult().isIsSuccess()) {
//            UserDetailBean.ResResultBean.CurDataBean curData = userDetailEvent.getResult().getCurData();
//            userInfo = new GroupMember(curData.getUserId(), curData.getUsername(), curData.getSex(), curData.getPhone(),
//                    curData.getEmail(), curData.getAvatar());
//        }
//        detailView.onUserInfoResult(userInfo);
//    }
//
//    @Override
//    public void doGetUserInfo(String userId) {
//        model.doGetUserInfo(userId);
//    }
//
//    @Override
//    public void removeEntry(String entryId) {
//        model.removeEntry(entryId);
//    }
//
//}
