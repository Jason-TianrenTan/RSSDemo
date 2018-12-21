package com.example.chidori.proxytestapp.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.chidori.proxytestapp.R;

public class PasswordActivity extends AppCompatActivity {

    private Button btn_reset;//重置按钮
    private EditText phone_number,new_psw,new_psw_again;
    private String phoneNumber,psw,pswAgain;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局 ,注册界面
        setContentView(R.layout.activity_password);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }
    private void init() {

        btn_reset = findViewById(R.id.btn_reset);
        phone_number = findViewById(R.id.phone_number);
        new_psw = findViewById(R.id.new_psw);
        new_psw_again = findViewById(R.id.new_psw_again);

        //重置按钮
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(PasswordActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(PasswordActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(PasswordActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!psw.equals(pswAgain)) {
                    Toast.makeText(PasswordActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                    //从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
                } else if (!isExistphoneNumber(phoneNumber)) {
                    Toast.makeText(PasswordActivity.this, "此手机号不存在", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(PasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    //保存手机号和密码到SharedPreferences中
                    savePasswordInfo(phoneNumber,psw);
                    //重置成功后把账号传递到LoginActivity.java中
                    Intent data = new Intent();
                    data.putExtra("phonenumber", phoneNumber);
                    setResult(RESULT_OK, data);
                    //RESULT_OK为Activity系统常量，状态码为-1，
                    // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
                    PasswordActivity.this.finish();
                }
            }
        });
    }

    /**     * 获取控件中的字符串     */
    private void getEditString(){
        phoneNumber=phone_number.getText().toString().trim();
        psw=new_psw.getText().toString().trim();
        pswAgain=new_psw_again.getText().toString().trim();
    }

    /**     * 从SharedPreferences中读取输入的手机号，判断SharedPreferences中是否有此手机号     */
    private boolean isExistphoneNumber(String phoneNumber){
        boolean has_phoneNumber=false;
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        String spPsw=sp.getString(phoneNumber, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_phoneNumber=true;
        }
        return has_phoneNumber;
    }

    /**     * 保存账号和密码到SharedPreferences中SharedPreferences     */
    private void savePasswordInfo(String phoneNumber,String psw){
        String md5Psw = MD5Utils.md5(psw);
        //把密码用MD5加密
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        SharedPreferences.Editor editor=sp.edit();
        //以手机号为key，密码为value保存在SharedPreferences中
        // key,value,如键值对，editor.putString(用户名，密码）;
        editor.remove(phoneNumber);
        editor.commit();
        editor.putString(phoneNumber, md5Psw);
        //提交修改 editor.commit();
        editor.commit();

    }
}