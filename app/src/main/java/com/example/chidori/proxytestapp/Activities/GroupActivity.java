package com.example.chidori.proxytestapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Activities.adapter.GroupCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.GroupPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupActivity extends AppCompatActivity implements Contract.IGroupView{
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private View view;

    private static GroupPresenterImpl presenter = new GroupPresenterImpl();
    private List<Group> cardList = new ArrayList<>();
    private GroupCardRecyclerAdapter recyclerAdapter = new GroupCardRecyclerAdapter(cardList);
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this,R.layout.activity_recycler,null);
        setContentView(view);

        presenter.attachView(this);

        setToolbar();

        toolbarTitle.setText("我的小组");
        presenter.doGetUserGroups();

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(GroupActivity.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerAdapter);
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
        toolbar.getMenu().findItem(R.id.add).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
            case R.id.add:{
                Intent intent = new Intent(GroupActivity.this, GroupNewActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return false;
    }

    public static GroupPresenterImpl getPresenter(){
        return presenter;
    }

    @Override
    public void onUserGroupsRetrieved(String status) {
        if(status.equals("success")){
            cardList = presenter.getUserGroups();
            if(cardList==null) cardList = new ArrayList<>();
            recyclerAdapter.resetCardList(cardList);
        }
        else {
            Toast.makeText(GroupActivity.this, "获得小组失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<>();
            recyclerAdapter.resetCardList(cardList);
        }
    }


    @Override
    public void onGroupCreated(String status) {
        if(status.equals("success")){
            Group newItem = new Group(UUID.randomUUID().toString(),StaticTool.opString,"","","","","");
            cardList.add(newItem);
            recyclerAdapter.resetCardList(cardList);
            Toast.makeText(GroupActivity.this, "小组创建成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(GroupActivity.this, "小组创建失败", Toast.LENGTH_SHORT).show();
        }
    }
}
