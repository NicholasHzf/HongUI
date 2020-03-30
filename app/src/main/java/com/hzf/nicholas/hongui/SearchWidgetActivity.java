package com.hzf.nicholas.hongui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hzf.nicholas.custom_ui.search_widget.SearchRecordLab;
import com.hzf.nicholas.custom_ui.search_widget.SearchWidget;

public class SearchWidgetActivity extends AppCompatActivity {

    private SearchWidget mSearchWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_widget);
        mSearchWidget = (SearchWidget)findViewById(R.id.search_widget);
        mSearchWidget.setSearchHint("请输入搜索信息");
        mSearchWidget.setHistoryTVText("历史记录");
        mSearchWidget.setOnIconClickListener(new SearchWidget.OnIconClickListener() {
            @Override
            public void onRightClick() {
                Toast.makeText(SearchWidgetActivity.this,"语音暂未实现！",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHistoryRecordClick(int position) {
                String content = SearchRecordLab.get(SearchWidgetActivity.this).getSearchRecords().get(position).getContent();
                Intent intent = new Intent(SearchWidgetActivity.this,TestActivity.class);
                intent.putExtra("INFO",content);
                startActivity(intent);
                Toast.makeText(SearchWidgetActivity.this,"点击："+content,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSearchAction(String content) {
                Intent intent = new Intent(SearchWidgetActivity.this,TestActivity.class);
                intent.putExtra("INFO",content);
                startActivity(intent);
                Toast.makeText(SearchWidgetActivity.this,"点击键盘搜索！"+content,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
