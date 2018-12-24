package com.example.chidori.proxytestapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.entity.IntroCard;
import com.example.chidori.proxytestapp.Activities.util.staticData;
import com.example.chidori.proxytestapp.R;

public class ElseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbarTitle;

    private IntroCard introCard = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_else);

        setToolbar();
        toolbarTitle.setText("详细信息");

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        if (position >= 0) {
            introCard = staticData.WDDRList.get(position);
            toolbarTitle.setText(introCard.getTitle());

        }


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
}
