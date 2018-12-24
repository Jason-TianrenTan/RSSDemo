package com.example.chidori.proxytestapp.Activities.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chidori.proxytestapp.R;


public class TabFragment extends Fragment {

    private int option;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView.Adapter recyclerAdapter;
    public static final int source = 0;
    public static final int publicEntry = 1;
    public static final int group_collection = 2;
    public static final int group_entry = 3;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_swipe, container, false);

        switch (option){
            case source:{
                recyclerAdapter = new SourceCardRecyclerAdapter(StaticTool.sourceCardList);
                break;
            }
            case publicEntry:{
                recyclerAdapter = new EntryCardRecyclerAdapter(StaticTool.getTestEntryCardList());
                break;
            }
            case group_collection:{
                recyclerAdapter = new CollectionCardRecyclerAdapter(StaticTool.getTestCollectionCardList(),false);
                break;
            }
            case group_entry:{
                recyclerAdapter = new EntryCardRecyclerAdapter(StaticTool.getTestEntryCardList());
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
                                recyclerAdapter = new EntryCardRecyclerAdapter(StaticTool.getTestEntryCardList());
                                break;
                            }
                            case group_collection:{
                                recyclerAdapter = new CollectionCardRecyclerAdapter(StaticTool.getTestCollectionCardList(),false);
                                break;
                            }
                            case group_entry:{
                                recyclerAdapter = new EntryCardRecyclerAdapter(StaticTool.getTestEntryCardList());
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
}
