package com.example.chidori.proxytestapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class GroupNewActivity extends AppCompatActivity {

    private CircleImageView imageView;
    private Button btn_newgroup;
    private EditText editText;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_new);

        imageView = (CircleImageView) findViewById(R.id.image_grouphead);
        btn_newgroup =(Button)findViewById(R.id.btn_new_group);
        editText =(EditText)findViewById(R.id.edit_groupname);

        setToolbar();
        toolbarTitle.setText("新建小组");

        btn_newgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_newgroup.setClickable(false);
                GroupActivity.getPresenter().doCreateGroup(editText.getText().toString(),"");
                StaticTool.opString = editText.getText().toString();
                finish();
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
