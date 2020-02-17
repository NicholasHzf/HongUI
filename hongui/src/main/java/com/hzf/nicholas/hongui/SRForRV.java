package com.hzf.nicholas.searchwidgetlib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SRForRV extends RecyclerView.Adapter<SRForRV.ViewHolder> {

    private List<SearchRecord> mSearchRecords;

    //静态内部类，用于缓存子项控件实例
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView srContent;
        //静态内部类的构造函数，参数view通常就是RecyclerView子项的最外层布局
        public ViewHolder (View view){
            super(view);
            srContent = (TextView)view.findViewById(R.id.search_record_item_tv);
        }
    }

    //构造函数
    public SRForRV(List<SearchRecord> searchRecords){
        mSearchRecords = searchRecords;
    }

    //用于告诉RecyclerView一共有多少子项，直接返回数据源的长度即可
    @Override
    public int getItemCount() {
        return mSearchRecords.size();
    }

    //用于对RecyclerView子项的数据进行赋值，会在每个子项被滚动到屏幕内的时候执行。
    @Override
    public void onBindViewHolder(SRForRV.ViewHolder holder, final int position) {
        SearchRecord searchRecord = mSearchRecords.get(position);
        holder.srContent.setText(searchRecord.getContent());
        holder.srContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    //用于创建ViewHolder实例
    @Override
    public SRForRV.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_record_item,parent,false);
        SRForRV.ViewHolder holder = new SRForRV.ViewHolder(view);
        return holder;
    }

    //点击事件
    //第一步 定义接口
    public interface OnItemClickListener {
        void onClick(int position);
    }

    private OnItemClickListener listener;

    //第二步， 写一个公共的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
