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

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.adapter.CollectionCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.GroupCollectionFragmentPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;

public class GroupCollectionFragment extends Fragment implements Contract.IGroupCollectionFragmentView {

    private static GroupCollectionFragmentPresenterImpl presenter = new GroupCollectionFragmentPresenterImpl();
    private View view;
    private String id;
    private List<Collection> cardList = new ArrayList<Collection>();
    private CollectionCardRecyclerAdapter recyclerAdapter = new CollectionCardRecyclerAdapter(cardList,false);

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recycler_swipe, container, false);

        presenter.attachView(this);

        presenter.doGetGroupCollections(id);

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
                                presenter.doGetGroupCollections(id);
                                swipeRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public static GroupCollectionFragmentPresenterImpl getPresenter(){
        return presenter;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public void onGroupCollectionsRetrieved(String status) {
        if(status.equals("success")){
            cardList = presenter.getCollections();
            if(cardList==null) cardList = new ArrayList<Collection>();
            recyclerAdapter.resetCardList(cardList);
        }
        else {
            Toast.makeText(getContext(), "获取收藏夹列表失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Collection>();
            recyclerAdapter.resetCardList(cardList);
        }
    }
}
