package com.example.chidori.proxytestapp.Activities.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.TabACPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;


public class TabFragment extends Fragment implements Contract.ITabACView{

    private int option;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView.Adapter recyclerAdapter;
    private List cardList;

    public static final int source = 0;
    public static final int publicEntry = 1;
    public static final int group_collection = 2;
    public static final int group_entry = 3;

    private static TabACPresenterImpl tabACPresenter;
    private View view;

    private String id = StaticTool.opId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_swipe, container, false);

        tabACPresenter = new TabACPresenterImpl();
        tabACPresenter.attachView(this);

        switch (option){
            case source:{
                recyclerAdapter = new SourceCardRecyclerAdapter(StaticTool.sourceCardList);
                break;
            }
            case publicEntry:{
                recyclerAdapter = new EntryCardRecyclerAdapter(StaticTool.getTestEntryCardList(),0);
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

        StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);

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
                                recyclerAdapter = new SourceCardRecyclerAdapter(StaticTool.sourceCardList);
                                break;
                            }
                            case publicEntry:{
                                recyclerAdapter = new EntryCardRecyclerAdapter(StaticTool.getTestEntryCardList(),0);
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
        recyclerAdapter = new CollectionCardRecyclerAdapter(StaticTool.getTestCollectionCardList(),false);
        StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);

        if(status.equals("success")){
            cardList = tabACPresenter.getCollections();
            if(cardList==null) cardList = new ArrayList<Collection>();
            recyclerAdapter = new CollectionCardRecyclerAdapter(cardList,true);
            StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);
        }
        else {
            Toast.makeText(getContext(), "获取收藏夹列表失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Collection>();
        }
    }

    @Override
    public void onGroupEntriesRetrieved(String status) {

        if(status.equals("success")){
            cardList = tabACPresenter.getEntries();
            if(cardList==null) cardList = new ArrayList<Entry>();
            recyclerAdapter = new EntryCardRecyclerAdapter(cardList,EntryCardRecyclerAdapter.tabAC);
            StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);
            StaticTool.opPosition=-1;
            StaticTool.opId=null;
        }
        else {
            Toast.makeText(getContext(), "获得文章失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Entry>();
        }
    }

    @Override
    public void onEntryAdded(String status) {

        if(status.equals("success")){
            Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
            recyclerAdapter.notifyItemChanged(StaticTool.opPosition);
            StaticTool.starList.add(StaticTool.opId);
        }
        else Toast.makeText(getContext(), "收藏失败", Toast.LENGTH_SHORT).show();

        StaticTool.opId = null;
        StaticTool.opPosition = -1;
    }

    public static TabACPresenterImpl getTabACPresenter(){
        return tabACPresenter;
    }
}