package com.example.chidori.proxytestapp.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Entry;
import com.example.chidori.proxytestapp.Activities.entity.Group;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Activities.util.CollectionCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.GroupCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.EntryCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.SourceCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Config;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.ListPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListActivity extends AppCompatActivity implements Contract.IListView{
    public static final int group = 0;
    public static final int collection = 1;
    public static final int source = 2;
    public static final int source_entry = 3;
    public static final int collection_entry = 4;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private int type = -1;
    private String id;
    private View view;
    private RadioGroup r;
    private String input;

    private List cardList;
    private static RecyclerView.Adapter recyclerAdapter;

    private static ListPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this,R.layout.activity_recycler,null);
        setContentView(view);

        type = getIntent().getIntExtra("type",-1);
        id = getIntent().getStringExtra("id");
        presenter = new ListPresenterImpl();
        presenter.attachView(this);

        setToolbar();
        setCardList();
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

        if((type == source_entry)||(type==collection_entry)) toolbar.getMenu().findItem(R.id.add).setVisible(false);
        else toolbar.getMenu().findItem(R.id.add).setVisible(true);

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
                switch (type){
                    case group:{
                        Intent intent = new Intent(ListActivity.this, GroupNewActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    case collection:{
                        setInputDialog("新建收藏夹","请输入名称");
                        return true;
                    }
                    case source:{
                        setPopMenu(findViewById(R.id.add));
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void setPopMenu(View v){
        PopupMenu popupMenu = new PopupMenu(ListActivity.this,v);
        popupMenu.inflate(R.menu.input);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.input_link:{
                        setInputDialog("导入link","请输入link");
                        return true;
                    }
                    case R.id.input_opml:{
                        Toast.makeText(ListActivity.this, "opml文件", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        //在这里选择筛选的文件类型
                        intent.setType("*/*");//设置类型
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        startActivityForResult(intent, 1);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void setInputDialog(String title,String message){
        Context context = this;

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title).setMessage(message);
        View v = new View(context);
        if(type == collection) v = View.inflate(context,R.layout.view_dialog_collection, null);
        else v = View.inflate(context,R.layout.view_dialog_source, null);
        dialog.setView(v);

        View finalV = v;
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EditText et = (EditText) finalV.findViewById(R.id.dialog_input);
                input = et.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(getBaseContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    switch (type){
                        case source:{
                            StaticTool.sourceCardList.add(new Source(UUID.randomUUID().toString(),input,"","",0,"",""));
                            break;
                        }
                        case collection:{
                            r = (RadioGroup) finalV.findViewById(R.id.dialog_radio);
                            presenter.doCreateCollection(input,"",r.getCheckedRadioButtonId());
                            break;
                        }
                    }
                }
            }
        }).setNegativeButton("取消", null).show();
    }

    private void setCardList(){
        switch (type){
            case group:{
                toolbarTitle.setText("我的小组");
                presenter.doGetUserGroups(Config.userId);
                break;
            }
            case collection:{
                toolbarTitle.setText("我的收藏");
                presenter.doGetUserCollections();
                break;
            }
            case source:{
                toolbarTitle.setText("我的订阅");
                presenter.doGetUserSources();
                break;
            }
            case source_entry:{
                toolbarTitle.setText(getIntent().getStringExtra("title"));
                presenter.doGetEntriesBySource(id);
                break;
            }
            case collection_entry:{
                toolbarTitle.setText(getIntent().getStringExtra("title"));
                presenter.doGetEntriesByCollection(id);
                break;
            }
        }
    }

    @Override
    public void onUserCollectionsCall(String status) {

        if(status.equals("success")){
            cardList = presenter.getCollections();
            if(cardList==null) cardList = new ArrayList<Collection>();
            recyclerAdapter = new CollectionCardRecyclerAdapter(cardList,true);
            StaticTool.setCardRecyclerView(recyclerAdapter,view);
        }
        else {
            Toast.makeText(ListActivity.this, "获取收藏夹列表失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Collection>();
        }
    }

    @Override
    public void onCollectionDeleted(String status) {

        if(status.equals("success")){
            presenter.deleteCollection(id);
            StaticTool.collectionCardList.remove(StaticTool.opPosition);
            recyclerAdapter.notifyItemChanged(StaticTool.opPosition);
            Toast.makeText(ListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();

        }
        else Toast.makeText(ListActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
        StaticTool.opPosition = -1;
    }

    @Override
    public void onUserSourcesRetrieved(String status) {
        if(status.equals("success")){
            cardList = presenter.getSources();
            if(cardList==null) cardList = new ArrayList<Source>();
            recyclerAdapter = new SourceCardRecyclerAdapter(cardList,SourceCardRecyclerAdapter.list);
            StaticTool.setCardRecyclerView(recyclerAdapter,view);
        }
        else {
            Toast.makeText(ListActivity.this, "获得订阅列表失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Source>();
        }
    }

    @Override
    public void onSourceDeleted(String status) {
        if(status.equals("success")){
            presenter.deleteSource(id);
            StaticTool.sourceCardList.remove(StaticTool.opPosition);
            recyclerAdapter.notifyItemChanged(StaticTool.opPosition);
            Toast.makeText(ListActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ListActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
        }
        StaticTool.opPosition = -1;

    }

    @Override
    public void onEntriesBySourceRetrieved(String status) {
        if(status.equals("success")){
            cardList = presenter.getEntries();
            if(cardList==null) cardList = new ArrayList<Entry>();
            recyclerAdapter = new EntryCardRecyclerAdapter(cardList,EntryCardRecyclerAdapter.listAC);
            StaticTool.setCardRecyclerView(recyclerAdapter,view);
        }
        else {
            Toast.makeText(ListActivity.this, "获得文章失败", Toast.LENGTH_SHORT).show();
            cardList = new ArrayList<Entry>();
        }
        StaticTool.opPosition = -1;
    }

    @Override
    public void onEntriesByCollectionRetrieved(String status) {
        if(status.equals("success")){
            cardList = presenter.getEntries();
            if(cardList==null) cardList = new ArrayList<Entry>();
            recyclerAdapter = new EntryCardRecyclerAdapter(cardList,EntryCardRecyclerAdapter.listAC);
            StaticTool.setCardRecyclerView(recyclerAdapter,view);
        }
        else {
            Toast.makeText(ListActivity.this, "获得文章失败", Toast.LENGTH_SHORT).show();
            cardList =new ArrayList<Entry>();
        }
        StaticTool.opPosition = -1;
    }

    @Override
    public void onEntryAddedToCollection(String status) {
        if(status.equals("success")){
            Toast.makeText(ListActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
            recyclerAdapter.notifyItemChanged(StaticTool.opPosition);
            StaticTool.starList.add(id);
        }
        else Toast.makeText(ListActivity.this, "收藏失败", Toast.LENGTH_SHORT).show();
        //zzzz
        StaticTool.opPosition = -1;
    }

    @Override
    public void onCollectionCreated(String status) {
        if(status.equals("success")){
            StaticTool.collectionCardList.add(new Collection("id",input,"",r.getCheckedRadioButtonId(),"",""));
            Toast.makeText(ListActivity.this, "收藏夹创建成功", Toast.LENGTH_SHORT).show();
            ListActivity.getRecyclerAdapter().notifyDataSetChanged();
            finish();
        }
        else {
            Toast.makeText(ListActivity.this, "收藏夹创建失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUserGroupsRetrieved(String status){
        if(status.equals("success")){
            cardList = presenter.getUserGroups();
            if(cardList==null) cardList = new ArrayList<Group>();
            recyclerAdapter = new GroupCardRecyclerAdapter(cardList);
            StaticTool.setCardRecyclerView(recyclerAdapter,view);
        }
        else {
            Toast.makeText(ListActivity.this, "获得小组失败", Toast.LENGTH_SHORT).show();
            cardList =new ArrayList<Group>();
        }
        StaticTool.opPosition = -1;
    }


    public static ListPresenterImpl getPresenter(){
        return presenter;
    }

    public static RecyclerView.Adapter getRecyclerAdapter(){
        return recyclerAdapter;
    }
}