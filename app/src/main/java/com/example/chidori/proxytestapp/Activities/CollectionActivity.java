package com.example.chidori.proxytestapp.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.adapter.CollectionCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.CollectionPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity implements Contract.ICollectionView {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private View view;

    private int index;
    private String input;

    private static CollectionPresenterImpl presenter = new CollectionPresenterImpl();
    private List<Collection> cardList = StaticTool.collectionCardList;
    private CollectionCardRecyclerAdapter recyclerAdapter = new CollectionCardRecyclerAdapter(cardList,true);
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this,R.layout.activity_recycler,null);
        setContentView(view);

        presenter.attachView(this);

        setToolbar();

        toolbarTitle.setText("我的收藏");

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(CollectionActivity.this,LinearLayoutManager.VERTICAL,false));
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
                setInputDialog();
                return true;
            }
        }
        return false;
    }

    private void setInputDialog(){
        Context context = this;

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("新建收藏夹").setMessage("请输入名称");
        View finalV = View.inflate(context,R.layout.view_dialog_collection, null);
        dialog.setView(finalV);

        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EditText et = (EditText) finalV.findViewById(R.id.dialog_input);
                input = et.getText().toString();
                if (input.equals("")) {
                    Toast.makeText(getBaseContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    RadioGroup r = (RadioGroup) finalV.findViewById(R.id.dialog_radio);
                    int checkedId = r.getCheckedRadioButtonId();
                    index = r.indexOfChild(r.findViewById(checkedId));
                    if(index == -1) index = 0;
                    presenter.doCreateCollection(input,"",index);
                }
            }
        }).setNegativeButton("取消", null).show();
    }

    public static CollectionPresenterImpl getPresenter(){
        return presenter;
    }

    @Override
    public void onCollectionDeleted(String status) {
        if(status.equals("success")){
            Toast.makeText(CollectionActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
            StaticTool.collectionCardList.remove(StaticTool.opPosition);
            recyclerAdapter.notifyItemChanged(StaticTool.opPosition);

        }
        else Toast.makeText(CollectionActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCollectionCreated(String status) {
        if(status.equals("success")){
            StaticTool.collectionCardList.add(new Collection("id", input, "", index, "", ""));
            Toast.makeText(CollectionActivity.this, "收藏夹创建成功", Toast.LENGTH_SHORT).show();
            recyclerAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(CollectionActivity.this, "收藏夹创建失败", Toast.LENGTH_SHORT).show();
        }
    }
}
