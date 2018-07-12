package com.example.razu.newcsitproject.Home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.razu.newcsitproject.R;


public class Webviews extends AppCompatActivity {
    WebView wb;
    @Override
    public void onBackPressed(){
        if (wb.canGoBack()){
            wb.goBack();
        }else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviews);
        String urls = getIntent().getExtras().getString("url");

        wb = (WebView)findViewById(R.id.webview);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setFocusable(true);
        wb.setFocusableInTouchMode(true);
        wb.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        wb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setDatabaseEnabled(true);
        wb.getSettings().setAppCacheEnabled(true);
        wb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wb.loadUrl(urls);
        wb.setWebViewClient(new WebViewClient());

    }
}
