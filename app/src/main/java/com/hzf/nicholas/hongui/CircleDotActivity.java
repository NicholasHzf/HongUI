package com.hzf.nicholas.hongui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.hzf.nicholas.custom_ui.circle_dot.CircleDot;

public class CircleDotActivity extends AppCompatActivity {

    private CircleDot mCircleDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_dot);
        mCircleDot = findViewById(R.id.circle_dot);
        mCircleDot.setAbleSelect(true);
        mCircleDot.setColorStringInner("#1089EE");
        mCircleDot.setColorStringStroke("#ff0000");
        mCircleDot.setRadius(24);
        mCircleDot.setStrokeRatio(0.2f);
        mCircleDot.setSelected(true);
        mCircleDot.setOnClickListener(new CircleDot.OnClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(CircleDotActivity.this,"点击了CircleDot!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
