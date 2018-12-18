package com.example.chidori.proxytestapp.Activities;

import android.os.Bundle;

import com.example.chidori.proxytestapp.Activities.BaseActivity;
import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Events.LoginEvent;
import com.example.chidori.proxytestapp.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity implements Contract.ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void initToolbar() {

    }

    @Override
    public void initView() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventCall(LoginEvent loginEvent) {
        //处理
    }


}
