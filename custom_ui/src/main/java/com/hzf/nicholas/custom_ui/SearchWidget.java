package com.hzf.nicholas.custom_ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;


import org.litepal.LitePal;

import java.util.Arrays;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class SearchWidget extends LinearLayout {

    private ImageView mLeftIconIV;
    private int mLeftId;
    private ImageView mRightIconIV;
    private int mRightId;
    private ImageView mEmptyIV;
    private int mEmptyId;
    private AutoCompleteTextView mAutoCompleteTextView;
    private String mSearchHint;
    private TextView mHistoryTV;
    private String mHistoryText;
    private RelativeLayout mRelativeLayout;
    private RecyclerView mHistoryRV;
    private SRForRV mAdapter;
    private ArrayAdapter mArrayAdapter;

    public SearchWidget(final Context context, AttributeSet attrs) {
        super(context,attrs);
        //加载自定义布局
        LayoutInflater.from(context).inflate(R.layout.search_widget,this);
        //本地数据库初始化
        LitePal.initialize(context);
        //实例化组件
        mLeftIconIV = (ImageView) findViewById(R.id.widget_search_left_icon_iv);
        mRightIconIV = (ImageView) findViewById(R.id.widget_search_right_icon_iv);
        mEmptyIV = (ImageView) findViewById(R.id.widget_search_empty_iv);
        mAutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.widget_search_ac_tv);
        mRelativeLayout = (RelativeLayout)findViewById(R.id.widget_search_rl);
        mHistoryTV = (TextView) findViewById(R.id.widget_search_history_tv);
        mHistoryRV = (RecyclerView)findViewById(R.id.widget_search_history_rv);
        mAdapter = new SRForRV(SearchRecordLab.get(context).getSearchRecords());

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(context);
        layoutManager.setFlexWrap(FlexWrap.WRAP); //设置是否换行
        layoutManager.setAlignItems(AlignItems.STRETCH);
        mHistoryRV.setLayoutManager(layoutManager);
        mHistoryRV.setAdapter(mAdapter);

        mArrayAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,SearchRecordLab.get(context).getHistoryToStringList());
        mAutoCompleteTextView.setAdapter(mArrayAdapter);

        /*
            每一个属性集合编译之后都会对应一个styleable对象,
            通过styleable对象获取TypedArray typedArray，然后通过键值对获取属性值。
            R.styleable.SettingsItemLayout,SettingsItemLayout对应attrs里面属性集的名称而不是本类的类名
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchWidget);
        if (typedArray != null) {
            mSearchHint = typedArray.getString(R.styleable.SearchWidget_title_hint);
            mHistoryText = typedArray.getString(R.styleable.SearchWidget_history);
            mLeftId = typedArray.getResourceId(R.styleable.SearchWidget_left_icon_iv,R.drawable.icon_search);
            mRightId = typedArray.getResourceId(R.styleable.SearchWidget_right_icon_iv,R.drawable.icon_voice);
            mEmptyId = typedArray.getResourceId(R.styleable.SearchWidget_empty_icon_iv,R.drawable.icon_empty_bin);
            typedArray.recycle();
        }
        //将自定义的属性值设置到组件上
        if (mSearchHint != null){
            mAutoCompleteTextView.setHint(mSearchHint);
        }else {
            mSearchHint = getResources().getString(R.string.search_hint);
            mAutoCompleteTextView.setHint(mSearchHint);
        }
        if (mHistoryText != null){
            mHistoryTV.setText(mHistoryText);
        }else {
            mHistoryText = getResources().getString(R.string.search_history);
            mHistoryTV.setText(mHistoryText);
        }
        mLeftIconIV.setImageResource(mLeftId);
        mRightIconIV.setImageResource(mRightId);
        mEmptyIV.setImageResource(mEmptyId);

        //可对组件设置点击事件
        mEmptyIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                //通过AlertDialog.Builder创建一个AlertDialog的实例
                dialog.setTitle(getResources().getString(R.string.alert_dialog_title));
                dialog.setMessage(getResources().getString(R.string.alert_dialog_msg_for_search_activity));
                dialog.setCancelable(true);
                //为这个对话框设置标题，内容，可否取消属性
                dialog.setPositiveButton(getResources().getString(R.string.alert_dialog_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SearchRecordLab.get(context).clearSearchRecords();
                        mAdapter.notifyDataSetChanged();
                        mArrayAdapter.clear();
                        Toast.makeText(context,getResources().getString(R.string.alert_dialog_ok_toast),Toast.LENGTH_SHORT).show();
                    }
                });
                //调用setPositiveButton()方法为对话框设置确定按钮的点击事件
                dialog.setNegativeButton(getResources().getString(R.string.alert_dialog_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,getResources().getString(R.string.alert_dialog_cancel_toast),Toast.LENGTH_SHORT).show();
                    }
                });
                //调用setNegativeButton()方法为对话框设置取消按钮的点击事件
                dialog.show();//将对话框显示出来
            }
        });
        mRightIconIV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRightClick();
                }
            }
        });
        mAdapter.setOnItemClickListener(new SRForRV.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                if (listener != null) {
                    listener.onHistoryRecordClick(position);
                }
            }
        });
        mAutoCompleteTextView.setOnEditorActionListener(new AutoCompleteTextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String content = v.getText().toString();
                    SearchRecordLab.get(context).addSearchRecord(content);
                    mAdapter.notifyDataSetChanged();
                    v.setText("");
                    mArrayAdapter.add(content);
                    if (listener != null) {
                        listener.onSearchAction(content);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    //点击事件
    //第一步 定义接口
    public interface OnIconClickListener {
        void onRightClick();
        void onHistoryRecordClick(int position);
        void onSearchAction(String content);
    }

    private OnIconClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnIconClickListener(OnIconClickListener listener) {
        this.listener = listener;
    }

    public void setSearchHint(String text){
        mAutoCompleteTextView.setHint(text);
    }

    public void setHistoryTVText(String text){
        mHistoryTV.setText(text);
    }

}
