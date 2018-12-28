package com.example.chidori.proxytestapp.Activities.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chidori.proxytestapp.Activities.adapter.TabAdapter;
import com.example.chidori.proxytestapp.R;

public class NavHomeFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_tabs, null);

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager tab_viewPager = (ViewPager) view.findViewById(R.id.tab_viewPager);
        TabAdapter tab_adapter = new TabAdapter(getChildFragmentManager());

        String[] tabName = {"推荐","已订阅","更多"};

        //
        RecommendFragment tab0 = new RecommendFragment();
        tab_adapter.addFragment(tab0);
        tabs.addTab(tabs.newTab());
        //

        SourceFragment tab1 = new SourceFragment();
        tab_adapter.addFragment(tab1);
        tabs.addTab(tabs.newTab());

        PublicEntryFragment tab2 = new PublicEntryFragment();
        tab_adapter.addFragment(tab2);
        tabs.addTab(tabs.newTab());

        tab_viewPager.setAdapter(tab_adapter);
        tabs.setupWithViewPager(tab_viewPager);

        for(int i=0;i<tabName.length;i++){
            tabs.getTabAt(i).setText(tabName[i]);
        }
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        return view;
    }
}
