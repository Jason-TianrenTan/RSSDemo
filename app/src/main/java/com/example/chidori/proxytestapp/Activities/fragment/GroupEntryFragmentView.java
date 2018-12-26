package com.example.chidori.proxytestapp.Activities.fragment;

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

import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.adapter.EntryCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.GroupEntryFragmentPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;

public class GroupEntryFragmentView extends Fragment implements Contract.IGroupEntryFragmentView {

    private static GroupEntryFragmentPresenterImpl presenter = new GroupEntryFragmentPresenterImpl();
    private View view;
    private String id;
    private List<Entry> cardList = new ArrayList<Entry>();
    private EntryCardRecyclerAdapter recyclerAdapter = new EntryCardRecyclerAdapter(cardList,EntryCardRecyclerAdapter.group_entry);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_swipe, container, false);

        presenter.attachView(this);

        presenter.doGetGroupEntries(id);

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerAdapter);

        setRefresh();

        return view;
    }

    private void setRefresh(){
        SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                presenter.doGetGroupEntries(id);
                                swipeRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public static GroupEntryFragmentPresenterImpl getPresenter(){
        return presenter;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void onGroupEntriesRetrieved(String status) {
        if(status.equals("success")){
            cardList = presenter.getEntries();
            if(cardList==null) cardList = new ArrayList<>();
            recyclerAdapter.resetCardList(cardList);
        }
        else {
            Toast.makeText(getActivity(), "获得组内文章失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<>();
            recyclerAdapter.resetCardList(cardList);
        }
    }

    @Override
    public void onEntryAdded(String status) {
        if(status.equals("success")){
            Toast.makeText(getActivity(), "收藏成功", Toast.LENGTH_SHORT).show();
            recyclerAdapter.notifyItemChanged(StaticTool.opPosition);
            StaticTool.myEntryIdList.add(StaticTool.opString);
        }
        else Toast.makeText(getActivity(), "收藏失败", Toast.LENGTH_SHORT).show();
    }
}
