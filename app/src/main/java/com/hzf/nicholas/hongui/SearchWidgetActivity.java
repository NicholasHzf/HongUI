package com.hzf.nicholas.hongui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.hzf.nicholas.custom_ui.SearchWidget;

public class SearchWidgetActivity extends AppCompatActivity {

    private SearchWidget mSearchWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_widget);
        mSearchWidget = (SearchWidget)findViewById(R.id.search_widget);
        mSearchWidget.setSearchHint("修改搜索框默认提示信息");
        mSearchWidget.setHistoryTVText("修改搜索历史记录标题");
        mSearchWidget.setOnIconClickListener(new SearchWidget.OnIconClickListener() {
            @Override
            public void onRightClick() {
                Toast.makeText(SearchWidgetActivity.this,"语音暂未实现！",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHistoryRecordClick() {
                Toast.makeText(SearchWidgetActivity.this,"点击历史记录item",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchAction() {
                Toast.makeText(SearchWidgetActivity.this,"点击键盘搜索！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
