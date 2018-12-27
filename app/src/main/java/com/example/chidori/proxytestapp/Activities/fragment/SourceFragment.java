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

import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Activities.adapter.SourceCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.SourceFragmentPresenter;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class SourceFragment extends Fragment implements Contract.ISourceFragmentView{

    private SourceCardRecyclerAdapter recyclerAdapter = new SourceCardRecyclerAdapter(StaticTool.sourceCardList,SourceCardRecyclerAdapter.home);

    private View view;
    private static SourceFragmentPresenter presenter = new SourceFragmentPresenter();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_swipe, container, false);

        presenter.attachView(this);
        presenter.doGetUserSources();

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
                                presenter.doGetUserSources();
                                swipeRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public static SourceFragmentPresenter getPresenter(){
        return presenter;
    }

    @Override
    public void onSourceDeleted(String status) {
        if(status.equals("success")){
            StaticTool.sourceCardList.remove(StaticTool.opPosition);
            recyclerAdapter.resetCardList(StaticTool.sourceCardList);
            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserSourcesRetrieved(String status) {
        if(status.equals("success")){
            List<Source> list = presenter.getSources();
            if(list==null) return;
            StaticTool.sourceCardList = list;
            recyclerAdapter.resetCardList(StaticTool.sourceCardList);
        }
        else {
            Toast.makeText(getActivity(), "获得订阅列表失败", Toast.LENGTH_SHORT).show();
        }
    }
}
