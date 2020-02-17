package com.hzf.nicholas.hongui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSearchWidgetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //LitePal.initialize(this);
        setContentView(R.layout.activity_main);
        initElement();
        mSearchWidgetBtn.setOnClickListener(this);

    }

    private void initElement(){
        //组件初始化
        mSearchWidgetBtn = (Button) findViewById(R.id.activity_main_search_btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_search_btn:
                Intent intent = new Intent(this,SearchWidgetActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
