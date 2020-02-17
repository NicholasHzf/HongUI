package com.hzf.nicholas.searchwidgetlib;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.LitePal;

import java.util.List;

public class DBHelper {

    private static SQLiteDatabase db = LitePal.getDatabase();

    public static List<SearchRecord> getPopularRecords(){
        List<SearchRecord> records = LitePal.where("type == 1 ").find(SearchRecord.class);
        return records;
    }

    public static void insertPopularRecord(String content){
        SearchRecord sr = new SearchRecord();
        sr.setContent(content);
        sr.setType(1);
        sr.save();
    }

    public static List<SearchRecord> getHistoryRecords(){
        List<SearchRecord> records = LitePal.where("type == 0 ").find(SearchRecord.class);
        return records;
    }

    public static void insertHistoryRecord(String content){
        SearchRecord sr = new SearchRecord();
        sr.setContent(content);
        sr.setType(0);
        sr.save();
    }

    public static void deleteAllHistoryRecords(){
        LitePal.deleteAll(SearchRecord.class, "type == 0");
    }



}
