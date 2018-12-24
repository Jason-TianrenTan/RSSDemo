package com.example.chidori.proxytestapp.Activities.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chidori.proxytestapp.Activities.entity.IntroCard;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class TabFragment extends Fragment {

    private int option;
    private SwipeRefreshLayout swipeRefresh;
    IntroCardRecyclerAdapter recyclerAdapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_swipe, container, false);

        setRecyclerAdapter();

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_swipe_view);
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
                        recyclerAdapter.resetCardList(getCardList());
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private List<IntroCard> getCardList(){
        Log.e("option",option+"");
        switch (option){
            case staticData.WDDR:{
                return staticData.WDDRList;
            }
            case staticData.QBGX:{
                return staticData.getTestIntroCardList();
            }
        }
        return null;
    }

    private void setRecyclerAdapter(){
        recyclerAdapter = new IntroCardRecyclerAdapter(getCardList(), option);
    }

    public void setOption(int option) {
        this.option = option;
    }
}
