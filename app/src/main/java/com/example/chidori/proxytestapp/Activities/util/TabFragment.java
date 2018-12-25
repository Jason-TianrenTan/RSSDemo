package com.example.chidori.proxytestapp.Activities.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.NavigationHomePresenter;
import com.example.chidori.proxytestapp.Presenter.TabACPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;


public class TabFragment extends Fragment implements Contract.ITabACView,Contract.INavigationHomeView{

    private int option;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView.Adapter recyclerAdapter;
    private List cardList;

    public static final int source = 0;
    public static final int publicEntry = 1;
    public static final int group_collection = 2;
    public static final int group_entry = 3;

    private static TabACPresenterImpl tabACPresenter = new TabACPresenterImpl();
    private static NavigationHomePresenter homePresenter = new NavigationHomePresenter();
    private View view;

    private String id;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_swipe, container, false);


        switch (option){
            case source:{
                homePresenter.attachView(this);
                homePresenter.doGetSources(0);
                break;
            }
            case publicEntry:{
                homePresenter.attachView(this);
                homePresenter.doGetPublicEntries();
                break;
            }
            case group_collection:{
                tabACPresenter.attachView(this);
                tabACPresenter.doGetGroupCollections(id);
                break;
            }
            case group_entry:{
                tabACPresenter.attachView(this);
                tabACPresenter.doGetGroupEntries(id);
                break;
            }
        }

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerAdapter);

        swipeRefresh = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshTab();
            }
        });

        return view;
    }

    private void refreshTab(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        switch (option){
                            case source:{
                                homePresenter.doGetSources(0);
                                break;
                            }
                            case publicEntry:{
                                homePresenter.doGetPublicEntries();
                                break;
                            }
                            case group_collection:{
                                tabACPresenter.doGetGroupCollections(id);
                                break;
                            }
                            case group_entry:{
                                tabACPresenter.doGetGroupEntries(id);
                                break;
                            }
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    public void setOption(int option) {
        this.option = option;
    }

    @Override
    public void onGroupCollectionsRetrieved(String status) {
        if(status.equals("success")){
            cardList = tabACPresenter.getCollections();
            if(cardList==null) cardList = new ArrayList<Collection>();
            recyclerAdapter = new CollectionCardRecyclerAdapter(cardList,true);
            RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);         recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));         recyclerView.setAdapter(recyclerAdapter);
        }
        else {
            Toast.makeText(getContext(), "获取收藏夹列表失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Collection>();
        }
        StaticTool.opPosition=-1;
    }

    @Override
    public void onGroupEntriesRetrieved(String status) {
        if(status.equals("success")){
            cardList = homePresenter.getEntries();
            if(cardList==null) cardList = new ArrayList<Entry>();
            recyclerAdapter = new EntryCardRecyclerAdapter(cardList,EntryCardRecyclerAdapter.tabAC);
            RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);         recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));         recyclerView.setAdapter(recyclerAdapter);
        }
        else {
            Toast.makeText(getContext(), "获得文章失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Entry>();
        }
        StaticTool.opPosition=-1;
    }

    @Override
    public void onSourceGet(String status) {
        if(status.equals("success")){
            cardList = homePresenter.getSources();
            if(cardList==null) cardList = new ArrayList<Source>();
            recyclerAdapter = new SourceCardRecyclerAdapter(cardList,SourceCardRecyclerAdapter.home);
            RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(recyclerAdapter);
        }
        else {
            Toast.makeText(getContext(), "获得订阅列表失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Source>();
        }
        StaticTool.opPosition=-1;
    }

    @Override
    public void onSourceDeleted(String status) {
        if(status.equals("success")){
            StaticTool.sourceCardList.remove(StaticTool.opPosition);
            recyclerAdapter.notifyItemChanged(StaticTool.opPosition);
            Toast.makeText(getContext(), "删除成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(), "删除失败", Toast.LENGTH_SHORT).show();
        }
        StaticTool.opPosition=-1;
    }

    @Override
    public void onPublicEntriesRetrieved(String status) {
        if(status.equals("success")){
            cardList = tabACPresenter.getEntries();
            if(cardList==null) cardList = new ArrayList<Entry>();
            recyclerAdapter = new EntryCardRecyclerAdapter(cardList,EntryCardRecyclerAdapter.tabAC);
            RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);         recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));         recyclerView.setAdapter(recyclerAdapter);
        }
        else {
            Toast.makeText(getContext(), "获得文章失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Entry>();
        }
        StaticTool.opPosition=-1;
    }

    @Override
    public void onEntryAdded(String status) {
        if(status.equals("success")){
            Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
            recyclerAdapter.notifyItemChanged(StaticTool.opPosition);
            StaticTool.starList.add(StaticTool.temp);
        }
        else Toast.makeText(getContext(), "收藏失败", Toast.LENGTH_SHORT).show();
        StaticTool.opPosition = -1;
    }

    public static TabACPresenterImpl getTabACPresenter(){
        return tabACPresenter;
    }

    public static NavigationHomePresenter getHomePresenter(){
        return homePresenter;
    }

    public void setId(String id) {
        this.id = id;
    }
}