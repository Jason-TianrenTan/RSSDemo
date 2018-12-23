package com.example.chidori.proxytestapp.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.entity.GroupCard;
import com.example.chidori.proxytestapp.Activities.entity.EntryCard;
import com.example.chidori.proxytestapp.Activities.entity.CollectionCard;
import com.example.chidori.proxytestapp.Activities.entity.SourceCard;
import com.example.chidori.proxytestapp.Activities.util.CollectionCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.GroupCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.EntryCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.SourceCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    public static final int group = 0;
    public static final int collection = 1;
    public static final int source = 2;
    public static final int entry = 3;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private int type = -1;
    private String id;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this,R.layout.activity_recycler,null);
        setContentView(view);

        type = getIntent().getIntExtra("type",-1);
        id = getIntent().getStringExtra("id");
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

        if(type == entry) toolbar.getMenu().findItem(R.id.add).setVisible(false);
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
            case R.id.detail:{
                Intent intent = new Intent(ListActivity.this,GroupDetailActivity.class);
                intent.putExtra("id", id);
                Toast.makeText(ListActivity.this, id, Toast.LENGTH_SHORT).show();
                startActivity(intent);
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
                        String input = et.getText().toString();
                        if (input.equals("")) {
                            Toast.makeText(getBaseContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            switch (type){
                                case source:{
                                    StaticTool.sourceCardList.add(new SourceCard("id",input));
                                    break;
                                }
                                case collection:{
                                    RadioGroup r = (RadioGroup) finalV.findViewById(R.id.dialog_radio);
                                    StaticTool.collectionCardList.add(new CollectionCard("id",input,r.getCheckedRadioButtonId()));
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
                List<GroupCard> cardList = StaticTool.getTestGroupCardList();
                GroupCardRecyclerAdapter recyclerAdapter = new GroupCardRecyclerAdapter(cardList);
                StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);
                break;
            }
            case collection:{
                toolbarTitle.setText("我的收藏");
                List<CollectionCard> cardList = StaticTool.collectionCardList;
                CollectionCardRecyclerAdapter recyclerAdapter = new CollectionCardRecyclerAdapter(cardList,true);
                StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);
                break;
            }
            case source:{
                toolbarTitle.setText("我的订阅");
                List<SourceCard> cardList = StaticTool.sourceCardList;
                SourceCardRecyclerAdapter recyclerAdapter = new SourceCardRecyclerAdapter(cardList);
                StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);
                break;
            }
            case entry:{
                toolbarTitle.setText(getIntent().getStringExtra("title"));
                List<EntryCard> cardList = StaticTool.getTestEntryCardList();
                EntryCardRecyclerAdapter recyclerAdapter = new EntryCardRecyclerAdapter(cardList);
                StaticTool.setSourceCardRecyclerView(recyclerAdapter,view);
                break;
            }
        }
    }

}
