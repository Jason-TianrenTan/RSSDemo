package com.example.chidori.proxytestapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.entity.GroupMember;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Activities.util.UserCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.GroupDetailPresenterImpl;
import com.example.chidori.proxytestapp.Presenter.TabModelPresenterImpl;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class GroupDetailActivity extends AppCompatActivity implements Contract.IGroupDetailView,Contract.ITabView {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private View view;
    private Button btn;
    private String id;
    private boolean state = false;

    private GroupDetailPresenterImpl presenter;
    private TabModelPresenterImpl presenter_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this,R.layout.activity_group_detail,null);
        setContentView(view);

        setToolbar();
        toolbarTitle.setText("小组详情");

        btn = (Button)findViewById(R.id.group_delete);
        if(state) btn.setText("退出小组");
        else btn.setText("加入小组");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state) {
                    //退出小组
                    presenter.doQuitGroup(StaticTool.opId);
                }
                else {
                    //加入小组
                    presenter.doEnterGroup(StaticTool.opId);
                }
            }
        });

        id = getIntent().getStringExtra("id");
        presenter_1.doGetGroupMembers(StaticTool.opId);
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
        toolbar.getMenu().findItem(R.id.add).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==android.R.id.home){
            finish();
            return true;
        }
        else return false;
    }

    @Override
    public void onGroupEntered(String status) {
        if(status.equals("success")){
            Toast.makeText(GroupDetailActivity.this, "加入小组成功", Toast.LENGTH_SHORT).show();
            btn.setText("退出小组");
            state = true;
            StaticTool.opId=null;
            StaticTool.opPosition=-1;
        }
        else{
            Toast.makeText(GroupDetailActivity.this, "退出小组失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGroupQuit(String status) {
        if(status.equals("success")){
            Toast.makeText(GroupDetailActivity.this, "退出小组成功", Toast.LENGTH_SHORT).show();
            btn.setText("加入小组");
            state = false;
            StaticTool.opId=null;
            StaticTool.opPosition=-1;
        }
        else{
            Toast.makeText(GroupDetailActivity.this, "退出小组失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGroupMembersResult(String status) {
        if(status.equals("success")){
            List<GroupMember> cardList=presenter_1.getMembers();
            UserCardRecyclerAdapter recyclerAdapter = new UserCardRecyclerAdapter(cardList);
            StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);
            Toast.makeText(GroupDetailActivity.this, "小组成员", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(GroupDetailActivity.this, "小组成员-1", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUserInfoResult(GroupMember member) {

    }
}