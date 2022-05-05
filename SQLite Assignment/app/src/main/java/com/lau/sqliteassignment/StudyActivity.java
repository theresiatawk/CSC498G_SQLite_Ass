package com.lau.sqliteassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class StudyActivity extends AppCompatActivity {

    WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        // Linking the variable the the list view layout
        web_view = (WebView) findViewById(R.id.web_view);

        // Getting the link to open from the first activity
        Intent i = getIntent();
        String study_link = i.getStringExtra("study_link");

        // Enabling java script because by default it's not enabled
        web_view.getSettings().setJavaScriptEnabled(true);

        // Set a client to this web view because we are calling a website which is outside our local host
        web_view.setWebViewClient(new WebViewClient());

        web_view.loadUrl(study_link);



    }
}