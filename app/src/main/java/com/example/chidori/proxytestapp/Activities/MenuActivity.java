package com.example.chidori.proxytestapp.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.entity.IntroCard;
import com.example.chidori.proxytestapp.Activities.entity.StarCard;
import com.example.chidori.proxytestapp.Activities.util.navigationFragment;
import com.example.chidori.proxytestapp.Activities.util.staticData;
import com.example.chidori.proxytestapp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MenuActivity extends AppCompatActivity {
    private static Toolbar toolbar;
    private static TextView toolbarTitle;
    private static BottomNavigationView navigation;
    private static FragmentManager fragmentManager;
    private static int current;
    private static navigationFragment home;
    private static navigationFragment group;
    private static navigationFragment star;
    private static navigationFragment user;
    private boolean isExit=false;
    private static String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setToolbar();

        current = 0;
        toolbarTitle.setText("主页");
        home = navigationFragment.newInstance(current);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.menu_container, home).show(home).commit();

        setNavigation();
    }

    private void setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle = (TextView) findViewById(R.id.toolbar_txt);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 绑定toolbar跟menu
        getMenuInflater().inflate(R.menu.toolbar, menu);
        toolbar.getMenu().findItem(R.id.detail).setVisible(false);
        toolbar.getMenu().findItem(R.id.add).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add){
            switch (current){
                case 0:{
                    setPopMenu(findViewById(R.id.add));
                    return true;
                }
                case 1:{
                    Intent intent = new Intent(MenuActivity.this, ElseActivity.class);
                    startActivity(intent);
                    return true;
                }
                case 2:{
                    setInputDialog("新建收藏夹","请输入新收藏夹名称");
                    return true;
                }
            }
        }
        return false;
    }

    private void setPopMenu(View v){
        PopupMenu popupMenu = new PopupMenu(MenuActivity.this,v);
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
                        Toast.makeText(MenuActivity.this, "opml文件", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        //*****************************************
                        //在这里选择筛选的文件类型
                        //*****************************************
                        intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
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
            Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
        }
    }

    private void setInputDialog(String title,String message){
        Context context = this;
        EditText et = new EditText(context);
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String input = et.getText().toString();
                        if (input.equals("")) {
                            Toast.makeText(getBaseContext(), "输入不能为空", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            switch (current){
                                case 0:{
                                    staticData.WDDRList.add(new IntroCard(input,"default","default"));
                                    break;
                                }
                                case 2:{
                                    staticData.SCJList.add(new StarCard(input));
                                    break;
                                }
                            }
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void setNavigation(){
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return setCurrent(item.getItemId());
            }
        });
    }

    public static void changeFragment(int itemId){
        navigation.setSelectedItemId(itemId);
        setCurrent(itemId);
    }

    private static boolean setCurrent(int itemId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (itemId) {
            case R.id.navigation_home:
                if (current == 0) return true;
                current = 0;
                if(group!=null) transaction.hide(group);
                if(star!=null) transaction.hide(star);
                if(user!=null) transaction.hide(user);
                transaction.show(home).commit();
                home.atHomePage();
                toolbarTitle.setText("主页");
                toolbar.getMenu().findItem(R.id.add).setVisible(true);
                return true;

            case R.id.navigation_group:
                if (current == 1) return true;
                current = 1;
                if(home!=null) transaction.hide(home);
                if(star!=null) transaction.hide(star);
                if(user!=null) transaction.hide(user);
                if (group == null) {
                    group = navigationFragment.newInstance(current);
                    transaction.add(R.id.menu_container,group).commit();
                }
                else transaction.show(group).commit();
                toolbarTitle.setText("我的小组");
                toolbar.getMenu().findItem(R.id.add).setVisible(true);
                return true;

            case R.id.navigation_star:
                if (current == 2) return true;
                current = 2;
                if(home!=null) transaction.hide(home);
                if(group!=null) transaction.hide(group);
                if(user!=null) transaction.hide(user);
                if (star == null) {
                    star = navigationFragment.newInstance(current);
                    transaction.add(R.id.menu_container,star).commit();
                }
                else transaction.show(star).commit();
                toolbarTitle.setText("我的收藏");
                toolbar.getMenu().findItem(R.id.add).setVisible(true);
                return true;

            case R.id.navigation_user:
                if (current == 3) return true;
                current = 3;
                if(home!=null) transaction.hide(home);
                if(group!=null) transaction.hide(group);
                if(star!=null) transaction.hide(star);
                if (user == null) {
                    user = navigationFragment.newInstance(current);
                    transaction.add(R.id.menu_container,user).commit();
                }
                else transaction.show(user).commit();
                toolbarTitle.setText("用户设置");
                toolbar.getMenu().findItem(R.id.add).setVisible(false);
                return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            if(!isExit) {
                isExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
            }
            else {
                moveTaskToBack(false);
            }
        }
        return false;
    }
}
