package com.example.chidori.proxytestapp.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Contract.Contract;
import com.example.chidori.proxytestapp.Presenter.ReaderPresenterImpl;
import com.example.chidori.proxytestapp.R;
import com.example.chidori.proxytestapp.Utils.IO.UniversalPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReaderActivity extends AppCompatActivity implements Contract.IReaderView {


    @BindView(R.id.webview) WebView webView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.page_title) TextView titleTextView;

    Contract.IReaderPresenter presenter;
    String testURL = "https://blog.csdn.net/baidu_33634330/article/details/77680289";

    @Override
    public void onTitleFound(String title) {
        titleTextView.setText(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initPresenter();
        initWebView();

        //测试
        test();
    }



    private void test() {
        UniversalPresenter testPresenter = new UniversalPresenter();
    }

    private void initPresenter() {
        presenter = new ReaderPresenterImpl();
        ((ReaderPresenterImpl) presenter).attachView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reader, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_favorite:
                Toast.makeText(this, "已添加到收藏夹", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }


    private void initWebView() {
        webView.loadUrl(testURL);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url); return true;
            }
        });

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);

        WebChromeClient webChromeClient=new WebChromeClient() {
            //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
            @Override
            public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
                localBuilder.setMessage(message).setPositiveButton("确定", null);
                localBuilder.setCancelable(false);
                localBuilder.create().show();
                result.confirm();
                return true;
            }

            //获取网页标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                presenter.setTitle(title);
                Log.i("ansen", "网页标题:" + title);
            }

            //加载进度回调
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

            }
        };
        webView.setWebChromeClient(webChromeClient);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_SEND.equals(action)) {
            String url = intent.getStringExtra(Intent.EXTRA_TEXT);
            webView.loadUrl(url);
            presenter.doLoadURL(url);
        }

    }


}
