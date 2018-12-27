package com.example.chidori.proxytestapp.Activities;

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

import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.adapter.EntryCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.adapter.SourceCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.EntryByCollectionActivityPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;

public class EntryByCollectionActivity extends AppCompatActivity implements Contract.IEntryByCollectionActivityView{
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private View view;
    private String id;

    private static EntryByCollectionActivityPresenterImpl presenter = new EntryByCollectionActivityPresenterImpl();
    private List<Entry> cardList = new ArrayList<>();
    private EntryCardRecyclerAdapter recyclerAdapter = new EntryCardRecyclerAdapter(cardList,SourceCardRecyclerAdapter.list);
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this,R.layout.activity_recycler,null);
        setContentView(view);

        id = getIntent().getStringExtra("id");
        presenter.attachView(this);

        setToolbar();

        toolbarTitle.setText(getIntent().getStringExtra("title"));
        presenter.doGetEntriesByCollection(id);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(EntryByCollectionActivity.this,LinearLayoutManager.VERTICAL,false));
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return false;
    }

    public static EntryByCollectionActivityPresenterImpl getPresenter(){
        return presenter;
    }

    @Override
    public void onEntriesByCollectionRetrieved(String status) {
        if(status.equals("success")){
            cardList = presenter.getEntries();
            if(cardList==null) cardList = new ArrayList<>();
            recyclerAdapter.resetCardList(cardList);
        }
        else {
            Toast.makeText(EntryByCollectionActivity.this, "获得文章失败", Toast.LENGTH_SHORT).show();
            cardList =new ArrayList<>();
            recyclerAdapter.resetCardList(cardList);
        }
    }

    @Override
    public void onEntryAdded(String status) {
        if(status.equals("success")){
            Toast.makeText(EntryByCollectionActivity.this,"收藏成功", Toast.LENGTH_SHORT).show();
            StaticTool.myEntryIdList.add(StaticTool.opString);
            recyclerAdapter.resetCardList(cardList);
        }
        else Toast.makeText(EntryByCollectionActivity.this,"收藏失败", Toast.LENGTH_SHORT).show();
    }
}
