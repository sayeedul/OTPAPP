package com.sayeedul.otpapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView textview;
    WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        Intent i = getIntent();
        String userName = i.getStringExtra("username");

        textview = (TextView)findViewById(R.id.userTV);
        textview.setText(textview.getText()+" "+userName+". SEARCH ANYTHING...");

        webview = (WebView)findViewById(R.id.webid) ;
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.google.com");

        WebSettings websettings = webview.getSettings();
        websettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {

        if(webview.canGoBack())
        {
            webview.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}


