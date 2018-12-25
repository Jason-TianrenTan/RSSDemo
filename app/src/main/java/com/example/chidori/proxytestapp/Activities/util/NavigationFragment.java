package com.example.chidori.proxytestapp.Activities.util;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.ListActivity;
import com.example.chidori.proxytestapp.Activities.LoginActivity;
import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.NavMenuPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationFragment extends Fragment implements Contract.INavMenuView {

    private NavMenuPresenterImpl presenter;
    private View view;
    private List cardList;

    public static NavigationFragment newInstance(int info) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putInt("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int info = getArguments().getInt("info");
        switch (info){
            case 0:{
                view = inflater.inflate(R.layout.fragment_tabs, null);
                setHome(view);
                break;
            }
            case 1:{
                view = inflater.inflate(R.layout.fragment_group, container,false);
                presenter = new NavMenuPresenterImpl();
                presenter.attachView(this);
                setGroup(view);
                break;
            }
            case 2:{
                view = inflater.inflate(R.layout.fragment_user, null);
                setUser(view);
                break;
            }
        }
        return view;
    }

    private void setHome(View view){
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager tab_viewPager = (ViewPager) view.findViewById(R.id.tab_viewPager);
        TabAdapter tab_adapter = new TabAdapter(getChildFragmentManager());

        String[] tabName = {"已订阅","更多"};

        TabFragment tab1 = new TabFragment();
        tab1.setOption(TabFragment.source);
        tab_adapter.addFragment(tab1);
        tabs.addTab(tabs.newTab());
        TabFragment tab2 = new TabFragment();
        tab2.setOption(TabFragment.publicEntry);
        tab_adapter.addFragment(tab2);
        tabs.addTab(tabs.newTab());

        tab_viewPager.setAdapter(tab_adapter);
        tabs.setupWithViewPager(tab_viewPager);

        for(int i=0;i<tabName.length;i++){
            tabs.getTabAt(i).setText(tabName[i]);
        }
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void setGroup(View view){
        SearchView searchView =(SearchView)view.findViewById(R.id.search);  //搜索框
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
    }

    private void setUser(View view){

        Button button=(Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        TextView tx = (TextView)view.findViewById(R.id.textView);
        tx.setText(Config.username);
        LinearLayout my_import=(LinearLayout)view.findViewById(R.id.my_source);
        my_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ListActivity.class);
                intent.putExtra("type",ListActivity.source);
                startActivity(intent);
            }
        });

        LinearLayout my_favor=(LinearLayout)view.findViewById(R.id.my_collection);
        my_favor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ListActivity.class);
                intent.putExtra("type",ListActivity.collection);
                startActivity(intent);
            }
        });

        LinearLayout my_group=(LinearLayout)view.findViewById(R.id.my_group);
        my_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ListActivity.class);
                intent.putExtra("type",ListActivity.group);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGroupSearchResult(String status) {
        if(status.equals("success")){
            cardList = presenter.getGroups();
            if(cardList==null) cardList = new ArrayList<Group>();
            GroupCardRecyclerAdapter recyclerAdapter = new GroupCardRecyclerAdapter(cardList);
            StaticTool.setCardRecyclerView(recyclerAdapter,view);
        }
        else {
            Toast.makeText(getContext(), "获得文章失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Group>();
        }
        StaticTool.opPosition=-1;
    }
}