package com.example.chidori.proxytestapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.entity.IntroCard;
import com.example.chidori.proxytestapp.Activities.util.IntroCardRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.staticData;
import com.example.chidori.proxytestapp.R;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private List<IntroCard> cardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        setToolbar();
        setCardList();
        setRecyclerView(cardList);
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
        toolbar.getMenu().findItem(R.id.detail).setVisible(false);
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

    private void setCardList(){
        Bundle args = getIntent().getExtras();
        if(args == null) {
            cardList = staticData.WDDRList;
            toolbarTitle.setText("我的导入");
        }
        else {
            int type = args.getInt("type");
            String str;
            switch (type){
//                case staticData.WDDR:{
//                    str = args.getString("input");
//                    staticData.WDDRList.add(new IntroCard(str,"default","default"));
//                    cardList = staticData.WDDRList;
//                    toolbarTitle.setText("我的导入");
//                    break;
//                }
                case staticData.SCJ:{
                    str = args.getString("id");
                    cardList = staticData.getTestIntroCardList();
                    toolbarTitle.setText(str);
                    break;
                }
            }
        }
    }

    private void setRecyclerView(List<IntroCard> cardList){
        IntroCardRecyclerAdapter recyclerAdapter = new IntroCardRecyclerAdapter(cardList,staticData.SCJ);

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_swipe_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(recyclerAdapter);
    }
}
