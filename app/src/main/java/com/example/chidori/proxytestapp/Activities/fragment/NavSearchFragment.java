package com.example.chidori.proxytestapp.Activities.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.adapter.GroupCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.NavMenuPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;

public class NavSearchFragment extends Fragment implements Contract.INavMenuView{

    private View view;
    private static NavMenuPresenterImpl presenter = new NavMenuPresenterImpl();

    private List<Group> cardList = new ArrayList<>();
    private GroupCardRecyclerAdapter recyclerAdapter = new GroupCardRecyclerAdapter(cardList);
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_search, container,false);

        presenter.attachView(this);

        SearchView searchView =(SearchView)view.findViewById(R.id.search);
        SearchView.SearchAutoComplete text = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        searchView.setQueryHint("搜索小组");
        searchView.setIconified(false);
        searchView.setFocusable(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.doSearchGroup(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }

    public static NavMenuPresenterImpl getPresenter(){
        return presenter;
    }

    @Override
    public void onGroupSearchResult(String status) {
        if(status.equals("success")){
            cardList = presenter.getGroups();
            if(cardList==null) cardList = new ArrayList<>();
            recyclerAdapter.resetCardList(cardList);
        }
        else {
            Toast.makeText(getContext(), "获得文章失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<>();
            recyclerAdapter.resetCardList(cardList);
        }
    }
}