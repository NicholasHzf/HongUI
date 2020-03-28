package com.hzf.nicholas.custom_ui.util;

import android.graphics.Paint;

public class RenderUtil {

    public static float getBaseline(float top, float bottom, Paint paint){
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (top + bottom - fontMetrics.bottom - fontMetrics.top) / 2;
    }

    public static Paint getPaint(int color){
        Paint paint = new Paint();
        paint.setColor(color);
        //开启抗锯齿
        paint.setAntiAlias(true);
        //开启防抖动
        paint.setDither(true);
        return paint;
    }

}
