package com.hzf.nicholas.custom_ui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateAndTime {

//    //获取当天的年月日
//    public static String getTodayDate(){
//        Calendar calendar = Calendar.getInstance();
//        Date today = calendar.getTime();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String result = format.format(today);
//        return result;
//    }

    //获取包含当天的一周的日期
    public static List<String> getThisWeekDates() {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = 0 ; i < 7 ; i++) {
            pastDaysList.add(getThisWeekDate(i));
        }
        return pastDaysList;
    }

    //获取本周第几天的日期
    public static String getThisWeekDate(int future){
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(getWeekStart());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + future);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取包含当天的一周的星期
     * @param mode 0 China 1 English
     * @return
     */
    public static List<String> getThisWeekDays(int mode) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = 0 ; i < 7 ; i++) {
            pastDaysList.add(getThisWeekDay(i,mode));
        }
        return pastDaysList;
    }

    //获取本周第几天的星期
    public static String getThisWeekDay(int future,int mode){
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(getWeekStart());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + future);
        Date today = calendar.getTime();
        SimpleDateFormat format = null;
        if (mode == 0){
            format = new SimpleDateFormat("EE",Locale.CHINA);
        }else {
            format = new SimpleDateFormat("EE",Locale.ENGLISH);
        }
        String result = format.format(today);
        return result;
    }

//    //获取包含当天的一周的年月日
//    public static List<String> getDates(int intervals) {
//        ArrayList<String> pastDaysList = new ArrayList<>();
//        for (int i = 0 ; i < intervals ; i++) {
//            pastDaysList.add(getThisWeekDate(i));
//        }
//        return pastDaysList;
//    }
//
//    //获取本周第几天的年月日
//    public static String getThisWeekDate(int future){
//        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = null;
//        try {
//            date = df.parse(getWeekStart());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Calendar calendar =Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + future);
//        Date today = calendar.getTime();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        String result = format.format(today);
//        return result;
//    }

    //获取未来7天的日期
    public static List<String> getFutureSevenDates() {
        ArrayList<String> daysList = new ArrayList<>();
        for (int i = 0 ; i < 7 ; i++) {
            daysList.add(getFutureDate(i));
        }
        return daysList;
    }

    //获取未来某天的日期
    public static String getFutureDate(int future){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + future);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来7天的星期
     * @param mode 0 China 1 English
     * @return
     */
    public static List<String> getFutureDays(int mode) {
        ArrayList<String> daysList = new ArrayList<>();
        for (int i = 0 ; i < 7 ; i++) {
            daysList.add(getFutureDay(i,mode));
        }
        return daysList;
    }

    //获取未来某天的星期
    public static String getFutureDay(int future,int mode){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + future);
        Date today = calendar.getTime();
        SimpleDateFormat format = null;
        if (mode == 0){
            format = new SimpleDateFormat("EE", Locale.CHINA);
        }else {
            format = new SimpleDateFormat("EE", Locale.ENGLISH);
        }
        String result = format.format(today);
        return result;
    }

    /**
     * 获取本周的第一天
     * @return String
     * **/
    public static String getWeekStart(){
        int mondayPlus;
        Calendar cd = Calendar.getInstance();

        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else if (dayOfWeek == 0){
            mondayPlus = -6;
        }else {
            mondayPlus = 1 - dayOfWeek;
        }
        cd.add(Calendar.DATE, mondayPlus);
        Date monday = cd.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(monday);
    }
}
