package com.hzf.nicholas.hongui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSearchWidgetBtn;
    private Button mWeekDisplayBtn;
    private Button mCircleDotBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElement();
        mSearchWidgetBtn.setOnClickListener(this);
        mWeekDisplayBtn.setOnClickListener(this);
        mCircleDotBtn.setOnClickListener(this);

    }

    private void initElement(){
        //组件初始化
        mSearchWidgetBtn = findViewById(R.id.activity_main_search_btn);
        mWeekDisplayBtn = findViewById(R.id.activity_main_week_display_btn);
        mCircleDotBtn = findViewById(R.id.activity_main_circle_dot_btn);
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
            case R.id.activity_main_circle_dot_btn:
                Intent intent3 = new Intent(this,CircleDotActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}
