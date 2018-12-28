package com.example.chidori.proxytestapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.fragment.GroupCollectionFragment;
import com.example.chidori.proxytestapp.Activities.fragment.GroupEntryFragmentView;
import com.example.chidori.proxytestapp.Activities.adapter.TabAdapter;
import com.example.chidori.proxytestapp.R;

public class GroupTabActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private View view;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this,R.layout.activity_tabs,null);
        setContentView(view);

        id = getIntent().getStringExtra("id");
        setToolbar();
        toolbarTitle.setText(getIntent().getStringExtra("title"));

        setTabs();
    }

    private void setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle = (TextView) findViewById(R.id.toolbar_txt);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 绑定toolbar跟menu
        getMenuInflater().inflate(R.menu.toolbar, menu);
        toolbar.getMenu().findItem(R.id.detail).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
            case R.id.detail:{
                Intent intent = new Intent(GroupTabActivity.this,GroupDetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("title",toolbarTitle.getText());
                startActivity(intent);
                return true;
            }
        }
        return false;
    }

    private void setTabs(){
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        ViewPager tab_viewPager = (ViewPager) view.findViewById(R.id.tab_viewPager);
        TabAdapter tab_adapter = new TabAdapter(getSupportFragmentManager());

        String[] tabName = {"收藏夹","内容"};

        GroupCollectionFragment tab1 = new GroupCollectionFragment();
        tab1.setId(id);
        tab_adapter.addFragment(tab1);
        tabs.addTab(tabs.newTab());

        GroupEntryFragmentView tab2 = new GroupEntryFragmentView();
        tab2.setId(id);
        tab_adapter.addFragment(tab2);
        tabs.addTab(tabs.newTab());

        tab_viewPager.setAdapter(tab_adapter);
        tabs.setupWithViewPager(tab_viewPager);

        for(int i=0;i<tabName.length;i++){
            tabs.getTabAt(i).setText(tabName[i]);
        }
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}