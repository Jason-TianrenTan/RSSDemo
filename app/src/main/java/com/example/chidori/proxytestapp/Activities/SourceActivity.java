package com.example.chidori.proxytestapp.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Activities.adapter.SourceCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.SourcePresenterImpl;
import com.example.chidori.proxytestapp.R;
import com.example.chidori.proxytestapp.Utils.Beans.SaveRSSBean;

import java.util.ArrayList;
import java.util.List;

public class SourceActivity extends AppCompatActivity implements Contract.ISourceView{
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private View view;
    private String path;

    private static SourcePresenterImpl presenter = new SourcePresenterImpl();
    private List<Source> cardList = StaticTool.sourceCardList;
    private SourceCardRecyclerAdapter recyclerAdapter = new SourceCardRecyclerAdapter(cardList,SourceCardRecyclerAdapter.list);
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this,R.layout.activity_recycler,null);
        setContentView(view);

        presenter.attachView(this);

        setToolbar();

        toolbarTitle.setText("我的订阅");

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(SourceActivity.this,LinearLayoutManager.VERTICAL,false));
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
                setPopMenu(findViewById(R.id.add));
                return true;
            }
        }
        return false;
    }

    private void setPopMenu(View v){
        PopupMenu popupMenu = new PopupMenu(SourceActivity.this,v);
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
                        Toast.makeText(SourceActivity.this, "opml文件", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(data==null)return;
            if(data.getData()==null) return;
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            path = uri.getPath();
        }
    }

    private void setInputDialog(String title,String message){
        Context context = this;

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title).setMessage(message);
        View finalV = View.inflate(context,R.layout.view_dialog_source, null);
        dialog.setView(finalV);

        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                EditText et = (EditText) finalV.findViewById(R.id.dialog_input);
                path = et.getText().toString();
                if (path.equals("")) {
                    Toast.makeText(getBaseContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                }
                else {
                    presenter.doAddRSSFromLink(path);
                }
            }
        }).setNegativeButton("取消", null).show();
    }

    public static SourcePresenterImpl getPresenter(){
        return presenter;
    }

    @Override
    public void onLinkResult(SaveRSSBean.ResResultBean bean) {
        if(bean.isIsSuccess()){
            StaticTool.sourceCardList.add(new Source(bean.getCurData().getSourceId(),bean.getCurData().getName(),
                    bean.getCurData().getDescription(),bean.getCurData().getLink(),bean.getCurData().getType(),
                    bean.getCurData().getCreateTime(),bean.getCurData().getUpdateTime()));
            recyclerAdapter.notifyDataSetChanged();
        }
        else Toast.makeText(SourceActivity.this, "添加订阅失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSourceDeleted(String status) {
        if(status.equals("success")){
            StaticTool.sourceCardList.remove(StaticTool.opPosition);
            recyclerAdapter.notifyItemChanged(StaticTool.opPosition);
            Toast.makeText(SourceActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(SourceActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
    }
}
