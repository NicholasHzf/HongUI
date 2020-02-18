package com.hzf.nicholas.hongui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private TextView searchTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        searchTV = (TextView) findViewById(R.id.widget_search_tv);
        String content = getIntent().getStringExtra("INFO");
        searchTV.setText(content);
    }
}
