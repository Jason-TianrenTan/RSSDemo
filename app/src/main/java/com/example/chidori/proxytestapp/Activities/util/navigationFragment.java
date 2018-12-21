package com.example.chidori.proxytestapp.Activities.util;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.chidori.proxytestapp.Activities.ListActivity;
import com.example.chidori.proxytestapp.Activities.MenuActivity;
import com.example.chidori.proxytestapp.Activities.entity.StarCard;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class navigationFragment extends Fragment {

    public static navigationFragment newInstance(int info) {
        navigationFragment fragment = new navigationFragment();
        Bundle args = new Bundle();
        args.putInt("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int info = getArguments().getInt("info");
        View view = null;
        switch (info){
            case 0:{
                Log.e("here","进入主页");
                view = inflater.inflate(R.layout.fragment_tabs, null);
                setHome(view);
                break;
            }
            case 1:{
                Log.e("here","进入小组");
                view = inflater.inflate(R.layout.fragment_else, null);
                setGroup(view);
                break;
            }
            case 2:{
                Log.e("here","进入收藏");
                view = inflater.inflate(R.layout.fragment_recycler,container,false);
                setStar(view);
                break;
            }
            case 3:{
                Log.e("here","进入我的");
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

        String[] tabName = {"我的导入","全部更新"};

        TabFragment tab1 = new TabFragment();
        tab1.setOption(staticData.WDDR);
        tab_adapter.addFragment(tab1);
        tabs.addTab(tabs.newTab());
        TabFragment tab2 = new TabFragment();
        tab2.setOption(staticData.QBGX);
        tab_adapter.addFragment(tab2);
        tabs.addTab(tabs.newTab());

        tab_viewPager.setAdapter(tab_adapter);
        tabs.setupWithViewPager(tab_viewPager);

        for(int i=0;i<tabName.length;i++){
            tabs.getTabAt(i).setText(tabName[i]);
        }
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void setGroup(View view){}


    private void setStar(View view){
        List<StarCard> cardList = staticData.SCJList;
        StarCardRecyclerAdapter recyclerAdapter = new StarCardRecyclerAdapter(cardList);

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void setUser(View view){

        Button button=(Button)view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getContext(),LoginActivity.class);
//                startActivity(intent);
            }
        });

        LinearLayout my_import=(LinearLayout)view.findViewById(R.id.my_import);
        my_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.changeFragment(R.id.navigation_home);
            }
        });

        LinearLayout my_favor=(LinearLayout)view.findViewById(R.id.my_favor);
        my_favor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.changeFragment(R.id.navigation_star);
            }
        });

        LinearLayout my_group=(LinearLayout)view.findViewById(R.id.my_group);
        my_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuActivity.changeFragment(R.id.navigation_group);
            }
        });
    }

    public void atHomePage(){
        View view = getView();
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.getTabAt(0).select();
    }
}