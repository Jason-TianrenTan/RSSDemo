package com.example.chidori.proxytestapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chidori.proxytestapp.Activities.util.CollectionRadioRecyclerAdapter;
import com.example.chidori.proxytestapp.Activities.util.StaticTool;
import com.example.chidori.proxytestapp.Activities.util.TabFragment;
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

    String currentLink = "";
    ReaderPresenterImpl presenter;

    @Override
    public void onTitleFound(String title) {
        titleTextView.setText(title);
    }

    @Override
    public void onEntryAdded(String status) {
        if (status.equals("success")) {
            Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initPresenter();
        initWebView();

        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("link");
            currentLink = url;
            webView.loadUrl(url);
        }
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(ReaderActivity.this);
                dialog.setTitle("选择收藏夹").setMessage("选择要添加进入的收藏夹");
                View v = View.inflate(ReaderActivity.this, R.layout.view_dialog_collection_radio, null);

                CollectionRadioRecyclerAdapter recyclerAdapter = new CollectionRadioRecyclerAdapter(StaticTool.collectionCardList);

                RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(ReaderActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(recyclerAdapter);
                dialog.setView(v);

                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String input = recyclerAdapter.getCheckedCollectionId();
                        if (input == null) {
                            Toast.makeText(ReaderActivity.this, "请选择收藏夹", Toast.LENGTH_SHORT).show();
                        } else {
                            presenter.doAddToCollection(presenter.getLink(), input, "来自" + presenter.getNickname(), presenter.getTitle());
                        }
                    }
                }).setNegativeButton("取消", null).show();
                break;
        }
        return true;
    }


    private void initWebView() {
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
            currentLink = url;
            webView.loadUrl(url);
            presenter.doLoadURL(url);
        }

    }


}
