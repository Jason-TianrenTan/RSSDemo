package com.example.chidori.proxytestapp.Activities.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chidori.proxytestapp.Activities.entity.CollectionCard;
import com.example.chidori.proxytestapp.Activities.entity.SourceCard;
import com.example.chidori.proxytestapp.R;

import java.util.List;


public class TabFragment extends Fragment {

    private int option;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView.Adapter recyclerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_swipe, container, false);

        if(option == 0) recyclerAdapter = new SourceCardRecyclerAdapter(StaticTool.sourceCardList);
        else recyclerAdapter = new CollectionCardRecyclerAdapter(StaticTool.getTestCollectionCardList());
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
                        if(option == 0) recyclerAdapter = new SourceCardRecyclerAdapter(StaticTool.sourceCardList);
                        else recyclerAdapter = new CollectionCardRecyclerAdapter(StaticTool.getTestCollectionCardList());
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
