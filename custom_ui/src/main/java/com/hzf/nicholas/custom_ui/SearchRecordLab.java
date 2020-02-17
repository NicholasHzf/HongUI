package com.hzf.nicholas.custom_ui;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SearchRecordLab {

    private static SearchRecordLab sSearchRecordLab;
    private List<SearchRecord> mSearchRecords;

    public static SearchRecordLab get(Context context){
        if (sSearchRecordLab == null){
            sSearchRecordLab = new SearchRecordLab(context);
        }
        return sSearchRecordLab;
    }

    private SearchRecordLab(Context context){
        mSearchRecords = new ArrayList<>();
        mSearchRecords.addAll(DBHelper.getHistoryRecords());
    }

    public List<SearchRecord> getSearchRecords() {
        return mSearchRecords;
    }

    public void addSearchRecord(String content){
        DBHelper.insertHistoryRecord(content);
        SearchRecord sr = new SearchRecord();
        sr.setType(0);
        sr.setContent(content);
        mSearchRecords.add(sr);
    }

    public void clearSearchRecords(){
        DBHelper.deleteAllHistoryRecords();
        mSearchRecords.clear();
    }

    public List<String> getHistoryToStringList(){
        List<String> strings = new ArrayList<>();
        for (SearchRecord searchRecord : mSearchRecords) {
            strings.add(searchRecord.getContent());
        }
        return strings;
    }

}
