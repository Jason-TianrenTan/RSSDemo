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

import com.example.chidori.proxytestapp.Activities.entity.UserCard;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Activities.util.UserCardRecyclerAdapter;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class GroupDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private Button btn;
    private String id;
    private boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this,R.layout.activity_group_detail,null);
        setContentView(view);

        setToolbar();
        toolbarTitle.setText("小组详情");

        id = getIntent().getStringExtra("id");

        List<UserCard> cardList = StaticTool.getTestUserCardList();
        UserCardRecyclerAdapter recyclerAdapter = new UserCardRecyclerAdapter(cardList);
        StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);

        btn = (Button)findViewById(R.id.group_delete);
        if(state) btn.setText("退出小组");
        else btn.setText("加入小组");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state) {
                    //退出小组
                    Toast.makeText(GroupDetailActivity.this, "退出小组成功", Toast.LENGTH_SHORT).show();
                    btn.setText("加入小组");
                    state = false;
                }
                else {
                    //加入小组
                    Toast.makeText(GroupDetailActivity.this, "加入小组成功", Toast.LENGTH_SHORT).show();
                    btn.setText("退出小组");
                    state = true;
                }
            }
        });

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
}