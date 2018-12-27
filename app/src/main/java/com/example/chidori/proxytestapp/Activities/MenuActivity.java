package com.example.chidori.proxytestapp.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
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

import com.example.chidori.proxytestapp.Activities.entity.Collection;
import com.example.chidori.proxytestapp.Activities.entity.Source;
import com.example.chidori.proxytestapp.Activities.fragment.NavHomeFragment;
import com.example.chidori.proxytestapp.Activities.fragment.NavSearchFragment;
import com.example.chidori.proxytestapp.Activities.fragment.NavUserFragment;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.MenuPresenterImpl;
import com.example.chidori.proxytestapp.R;
import com.example.chidori.proxytestapp.Utils.Beans.SaveRSSBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MenuActivity extends AppCompatActivity implements Contract.IMenuView {
    private static Toolbar toolbar;
    private static TextView toolbarTitle;

    private BottomNavigationView navigation;
    private static FragmentManager fragmentManager;
    private static NavHomeFragment homeFragment;
    private static NavSearchFragment searchFragment;
    private static NavUserFragment userFragment;

    private static int current;
    private static final int home = 0;
    private static final int search = 1;
    private static final int user = 2;

    private boolean isExit=false;
    private static String path;

    private static MenuPresenterImpl presenter = new MenuPresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setToolbar();

        presenter.attachView(this);
        presenter.doGetUserCollections();

        current = 0;
        toolbarTitle.setText("主页");
        homeFragment = new NavHomeFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.menu_container, homeFragment).show(homeFragment).commit();

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
        toolbar.getMenu().findItem(R.id.add).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add){
            switch (current){
                case home:{
                    setPopMenu(findViewById(R.id.add));
                    return true;
                }
                case search:{
                    Intent intent = new Intent(MenuActivity.this, GroupNewActivity.class);
                    startActivity(intent);
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
                        setInputDialog();
                        return true;
                    }
                    case R.id.input_opml:{
                        Toast.makeText(MenuActivity.this, "opml文件", Toast.LENGTH_SHORT).show();
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
//            presenter.doAddRSSFromLink(path);
        }
    }

    private void setInputDialog(){
        Context context = this;

        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("导入link").setMessage("请输入link");
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

    private void setNavigation(){
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return setCurrent(item.getItemId());
            }
        });
    }

    private static boolean setCurrent(int itemId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (itemId) {
            case R.id.navigation_home:{
                if (current == home) return true;
                current = home;
                if(searchFragment !=null) transaction.hide(searchFragment);
                if(userFragment !=null) transaction.hide(userFragment);
                transaction.show(homeFragment).commit();
                toolbarTitle.setText("主页");
                toolbar.getMenu().findItem(R.id.add).setVisible(true);
                return true;
            }
            case R.id.navigation_group:{
                if (current == search) return true;
                current = search;
                if(homeFragment !=null) transaction.hide(homeFragment);
                if(userFragment !=null) transaction.hide(userFragment);
                if (searchFragment == null) {
                    searchFragment = new NavSearchFragment();
                    transaction.add(R.id.menu_container, searchFragment).commit();
                }
                else transaction.show(searchFragment).commit();
                toolbarTitle.setText("搜索小组");
                toolbar.getMenu().findItem(R.id.add).setVisible(true);
                return true;
            }
            case R.id.navigation_user:{
                if (current == user) return true;
                current = user;
                if(homeFragment !=null) transaction.hide(homeFragment);
                if(searchFragment !=null) transaction.hide(searchFragment);
                if (userFragment == null) {
                    userFragment = new NavUserFragment();
                    transaction.add(R.id.menu_container, userFragment).commit();
                }
                else transaction.show(userFragment).commit();
                toolbarTitle.setText("用户设置");
                toolbar.getMenu().findItem(R.id.add).setVisible(false);
                return true;
            }
        }
        return false;
    }

    public static MenuPresenterImpl getPresenter(){
        return presenter;
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

    @Override
    public void onLinkResult(SaveRSSBean.ResResultBean bean) {
        if(bean.isIsSuccess()){
            StaticTool.sourceCardList.add(new Source(bean.getCurData().getSourceId(),bean.getCurData().getName(),
                    bean.getCurData().getDescription(),bean.getCurData().getLink(),bean.getCurData().getType(),
                    bean.getCurData().getCreateTime(),bean.getCurData().getUpdateTime()));
            Toast.makeText(MenuActivity.this, "添加订阅成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MenuActivity.this, "添加订阅失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUserCollectionsCall(String status) {
        if(status.equals("success")){
            List<Collection> list = presenter.getCollections();
            if(list==null) return;
            StaticTool.collectionCardList = list;
        }
        else {
            Toast.makeText(MenuActivity.this, "获取收藏夹列表失败", Toast.LENGTH_SHORT).show();
        }
    }

//    if(status.equals("success")){
//        List<String> list = presenter.getMyEntryIdList();
//        if(list==null) return;
//        StaticTool.myEntryIdList = list;
//    }
//        else {
//        Toast.makeText(MenuActivity.this, "获取已收藏文章id失败", Toast.LENGTH_SHORT).show();
//    }
}
