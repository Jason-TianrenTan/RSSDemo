package com.example.chidori.proxytestapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chidori.proxytestapp.R;


public class RegisterActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarTitle;

    //private TextView tv_main_title;//标题
    //private TextView tv_back;//返回按钮
    private Button btn_register;//注册按钮
    // 用户名，手机号，密码，再次输入的密码的控件
    private EditText et_user_name, phone_number, et_psw, et_psw_again;
    //用户名，手机号，密码，再次输入的密码的控件的获取值
    private String userName, phoneNumber, psw, pswAgain;
    //标题布局
    //private RelativeLayout rl_title_bar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局 ,注册界面
        setContentView(R.layout.activity_register);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        setToolbar();
        toolbarTitle.setText("注册账号");

    }

    private void setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbarTitle = (TextView) findViewById(R.id.toolbar_txt);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void init() {
        //从main_title_bar.xml 页面布局中获取对应的UI控件
        //tv_main_title=findViewById(R.id.tv_main_title);
        //tv_main_title.setText("注册");
        //tv_back=findViewById(R.id.tv_back);
        //从activity_register.xml 页面中获取对应的UI控件
        btn_register = findViewById(R.id.btn_register);
        et_user_name = findViewById(R.id.et_user_name);
        phone_number = findViewById(R.id.phone_number);
        et_psw = findViewById(R.id.et_psw);
        et_psw_again = findViewById(R.id.et_psw_again);
        //tv_back.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //   public void onClick(View v) {
        //返回键
        //      RegisterActivity.this.finish();
        //   }
        //  });

        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入在相应控件中的字符串
                getEditString();
                //判断输入框内容
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(RegisterActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!psw.equals(pswAgain)) {
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                    //从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
                } else if (isExistphoneNumber(phoneNumber)) {
                    Toast.makeText(RegisterActivity.this, "此手机号已经存在", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    //注册成功后把账号传递到LoginActivity.java中
                    Intent data = new Intent();
                    data.putExtra("phonenumber", phoneNumber);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    RegisterActivity.this.finish();
                }
            }
        });
    }

    /**
     * 获取控件中的字符串
     */
    private void getEditString() {
        userName = et_user_name.getText().toString().trim();
        phoneNumber = phone_number.getText().toString().trim();
        psw = et_psw.getText().toString().trim();
        pswAgain = et_psw_again.getText().toString().trim();
    }

    /**
     * 从SharedPreferences中读取输入的手机号，判断SharedPreferences中是否有此手机号
     */
    private boolean isExistphoneNumber(String phoneNumber) {
        boolean has_phoneNumber = false;
        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw = sp.getString(phoneNumber, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if (!TextUtils.isEmpty(spPsw)) {
            has_phoneNumber = true;
        }
        return has_phoneNumber;
    }

}