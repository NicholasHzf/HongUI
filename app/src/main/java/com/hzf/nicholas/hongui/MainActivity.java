package com.hzf.nicholas.hongui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSearchWidgetBtn;
    private Button mWeekDisplayBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElement();
        mSearchWidgetBtn.setOnClickListener(this);
        mWeekDisplayBtn.setOnClickListener(this);

    }

    private void initElement(){
        //组件初始化
        mSearchWidgetBtn = (Button) findViewById(R.id.activity_main_search_btn);
        mWeekDisplayBtn = (Button) findViewById(R.id.activity_main_week_display_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_search_btn:
                Intent intent = new Intent(this,SearchWidgetActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_main_week_display_btn:
                Intent intent2 = new Intent(this,WeeklyDisplayActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
