package com.hzf.nicholas.hongui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.hzf.nicholas.custom_ui.week_display.WeekDisplay;

public class WeeklyDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_display);

        int[] data = new int[7];
        for (int i = 0; i < 7; i++) {
            data[i] = i%4;
        }

        WeekDisplay weekDisplay = findViewById(R.id.second);
        weekDisplay.setDotColor(R.color.fc_blue_for_label);
        weekDisplay.setMode(2);
        weekDisplay.setData(data);
        weekDisplay.setOnItemClickListener(new WeekDisplay.OnItemClickListener() {
            @Override
            public void onClick(int position, String day, String date) {
                Toast.makeText(WeeklyDisplayActivity.this,"onClick"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
