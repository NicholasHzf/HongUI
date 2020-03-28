package com.hzf.nicholas.custom_ui.week_display;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.hzf.nicholas.custom_ui.R;
import com.hzf.nicholas.custom_ui.util.DateAndTime;
import com.hzf.nicholas.custom_ui.util.RenderUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class WeekDisplay extends View {

    private static final int MODE_NORMAL_THIS_WEEK = 0;
    private static final int MODE_ENGLISH_THREE_THIS_WEEK = 1;
    private static final int MODE_ENGLISH_SINGLE_THIS_WEEK = 2;
    private static final int MODE_NORMAL = 3;
    private static final int MODE_ENGLISH_THREE = 4;
    private static final int MODE_ENGLISH_SINGLE = 5;

    private static final int[] SELECTED = {R.attr.selected};
    private static final int[] UNSELECTED = {-R.attr.selected};

    private Context mContext;
    private Paint mDayPaint;
    private Paint mDatePaint;
    private Paint mDotPaint;
    private Paint mBgPaint;
    private Paint mSelectedDayPaint;
    private Paint mSelectedDatePaint;
    private Paint mSelectedBgPaint;

    private ColorStateList mBgColorList;
    private ColorStateList mTextDayColorList;
    private ColorStateList mTextDateColorList;
    private int mDotColorString;
    private boolean mSelected;
    private boolean mWithData;
    private int mMode;
    private float paddingDayLR;
    private float paddingDayTB;
    private float paddingDateDot;

    private int screenWidth;
    private float cellWidth;
    private float cellHeight;
    private List<String> weekText;
    private List<String> dayText;
    private int[] data;
    private int selectCurIndex = 0;

    public WeekDisplay(Context context) {
        this(context,null,0);
    }

    public WeekDisplay(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeekDisplay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.WeekDisplay);
        if (typedArray != null) {
            mBgColorList = typedArray.getColorStateList(R.styleable.WeekDisplay_bg_color);
            mTextDayColorList = typedArray.getColorStateList(R.styleable.WeekDisplay_text_day_color);
            mTextDateColorList = typedArray.getColorStateList(R.styleable.WeekDisplay_text_date_color);
            mDotColorString = typedArray.getColor(R.styleable.WeekDisplay_dot_color,Color.YELLOW);
            mSelected = typedArray.getBoolean(R.styleable.WeekDisplay_selected,false);
            mWithData = typedArray.getBoolean(R.styleable.WeekDisplay_with_data,false);
            mMode = typedArray.getInt(R.styleable.WeekDisplay_mode,0);
            paddingDayLR = typedArray.getDimension(R.styleable.WeekDisplay_padding_day_lr,0);
            paddingDayTB = typedArray.getDimension(R.styleable.WeekDisplay_padding_day_tb,0);
            paddingDateDot = typedArray.getDimension(R.styleable.WeekDisplay_padding_date_dot,0);
            typedArray.recycle();
        }

        if (mTextDayColorList == null){
            mDayPaint = RenderUtil.getPaint(Color.BLUE);
        }else{
            mDayPaint = RenderUtil.getPaint(mTextDayColorList.getColorForState(getDrawableState(),Color.BLUE));
        }
        if (mTextDateColorList == null){
            mDatePaint = RenderUtil.getPaint(Color.BLUE);
        }else{
            mDatePaint = RenderUtil.getPaint(mTextDateColorList.getColorForState(getDrawableState(),Color.BLUE));
        }
        if (mBgColorList == null){
            mBgPaint = RenderUtil.getPaint(Color.WHITE);
        }else {
            mBgPaint = RenderUtil.getPaint(mBgColorList.getColorForState(getDrawableState(),Color.WHITE));
        }
        mDotPaint = RenderUtil.getPaint(mDotColorString);
        setSelected(!mSelected);
        initData();
        initPaints();
        initTextSize();
    }

    private void initTextSize() {
        mDayPaint.setTextSize(cellHeight * 0.18f);
        mDatePaint.setTextSize(cellHeight * 0.15f);

        mSelectedDayPaint.setTextSize(cellHeight * 0.18f);
        mSelectedDatePaint.setTextSize(cellHeight * 0.15f);
    }

    private void initData() {
        WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;

        cellWidth = (float) screenWidth/7;
        cellHeight = cellWidth*1.5f;

        switch (mMode){
            case MODE_NORMAL_THIS_WEEK:
                weekText = DateAndTime.getThisWeekDays(0);
                dayText = DateAndTime.getThisWeekDates();
                break;
            case MODE_ENGLISH_THREE_THIS_WEEK:
                weekText = DateAndTime.getThisWeekDays(1);
                dayText = DateAndTime.getThisWeekDates();
                break;
            case MODE_ENGLISH_SINGLE_THIS_WEEK:
                List<String> fullText = DateAndTime.getThisWeekDays(1);
                weekText = new ArrayList<>();
                for (String s : fullText) {
                    weekText.add(s.substring(0,1));
                }
                dayText = DateAndTime.getThisWeekDates();
                break;
            case MODE_NORMAL:
                weekText = DateAndTime.getFutureDays(0);
                dayText = DateAndTime.getFutureSevenDates();
                break;
            case MODE_ENGLISH_THREE:
                weekText = DateAndTime.getFutureDays(1);
                dayText = DateAndTime.getFutureSevenDates();
                break;
            case MODE_ENGLISH_SINGLE:
                List<String> fullText2 = DateAndTime.getFutureDays(1);
                weekText = new ArrayList<>();
                for (String s : fullText2) {
                    weekText.add(s.substring(0,1));
                }
                dayText = DateAndTime.getThisWeekDates();
                break;
        }

        data = new int[7];
        for (int i = 0; i < 7; i++) {
            data[i] = 0;
        }
    }

    private void initPaints() {
        if (mTextDayColorList == null){
            mSelectedDayPaint = RenderUtil.getPaint(Color.WHITE);
        }else{
            mSelectedDayPaint = RenderUtil.getPaint(mTextDayColorList.getColorForState(getDrawableState(),Color.WHITE));
        }
        if (mTextDateColorList == null){
            mSelectedDatePaint = RenderUtil.getPaint(Color.WHITE);
        }else{
            mSelectedDatePaint = RenderUtil.getPaint(mTextDateColorList.getColorForState(getDrawableState(),Color.WHITE));
        }
        if (mBgColorList == null){
            mSelectedBgPaint = RenderUtil.getPaint(Color.BLUE);
        }else {
            mSelectedBgPaint = RenderUtil.getPaint(mBgColorList.getColorForState(getDrawableState(),Color.BLUE));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = screenWidth-14*6;
        int height = (int) (width/7*1.5f);
        if (!mWithData){
            height = (int) (width/7*1.5f*0.7f);
        }
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeWidth == MeasureSpec.AT_MOST) { // wrap_content
            sizeWidth = Math.min(width,sizeWidth);
            modeWidth = MeasureSpec.EXACTLY;
        }

        if (modeHeight == MeasureSpec.AT_MOST) { // wrap_content
            sizeHeight = Math.min(height,sizeHeight);
            modeHeight = MeasureSpec.EXACTLY;
        }

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(sizeWidth, modeWidth);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(sizeHeight, modeHeight);

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        int[] drawableState = super.onCreateDrawableState(extraSpace + 3);
        if (mSelected) {
            mergeDrawableStates(drawableState, SELECTED);
        } else {
            mergeDrawableStates(drawableState, UNSELECTED);
        }
        return drawableState;
    }

    @Override
    public void setSelected(boolean selected) {
        if (mSelected != selected){
            mSelected = selected;
            refreshDrawableState();
            initPaints();
            invalidate();
            mSelected = !selected;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        cellWidth = (float) getMeasuredWidth()/7;
        cellHeight = cellWidth*1.5f;

        initTextSize();
        mDotPaint = RenderUtil.getPaint(mDotColorString);
        if (paddingDayLR > cellWidth*0.2f){
            paddingDayLR = cellWidth*0.2f;
        }
        if (paddingDayTB > cellHeight*0.1f){
            paddingDayTB = cellHeight*0.1f;
        }
        for (int i = 0 ; i < 7 ; i++){
            RectF rectF = new RectF(i*cellWidth+cellWidth*0.2f-paddingDayLR,0,(i+1)*cellWidth-cellWidth*0.2f+paddingDayLR,cellHeight*0.7f);
            if (i == selectCurIndex){
                canvas.drawRoundRect(rectF,cellWidth*3/10+paddingDayLR,cellWidth*3/10+paddingDayLR,mSelectedBgPaint);
                drawText(canvas,i,0,mSelectedDayPaint,weekText.get(i));
                drawText(canvas,i,1,mSelectedDatePaint,dayText.get(i));
            }else {
                canvas.drawRoundRect(rectF,cellWidth*3/10+paddingDayLR,cellWidth*3/10+paddingDayLR,mBgPaint);
                drawText(canvas,i,0,mDayPaint,weekText.get(i));
                drawText(canvas,i,1,mDatePaint,dayText.get(i));
            }
            if (mWithData){
                drawDots(canvas,i,mDotPaint);
            }
        }
    }

    private void drawText(Canvas canvas, int index, int flag, Paint paint, String text) {
        if(isIllegalIndex(index)){
            return;
        }
        float top = cellHeight*0.1f+paddingDayTB;
        if (flag == 1){
            top = cellHeight*0.4f+paddingDayTB;
        }
        float bottom = top + cellHeight*0.3f-paddingDayTB;
        if (flag == 1){
            bottom = top + cellHeight*0.2f-paddingDayTB;
        }
        float baseline = RenderUtil.getBaseline(top, bottom, paint);
        float unitWidth = (cellWidth-paint.measureText(weekText.get(index)))/2;
        if (flag == 1){
            unitWidth = (cellWidth-paint.measureText(dayText.get(index)))/2;
        }
        int startX = (int) (index*cellWidth+unitWidth);
        canvas.drawText(text, startX, baseline, paint);
    }

    private void drawDots(Canvas canvas, int index, Paint paint) {
        if(isIllegalIndex(index) || data[index] == 0 || data[index] > 3){
            return;
        }
        float radius = cellWidth*0.05f;
        float unitWidth = cellWidth/2;
        float startX = index*cellWidth+unitWidth;//data[index] == 1
        float startY = cellHeight*0.8f+paddingDateDot;
        if (data[index] == 2){
            startX = index*cellWidth+unitWidth-2*radius;//data[index] == 2
            canvas.drawCircle(startX,startY,radius,paint);
            startX = index*cellWidth+unitWidth+2*radius;//data[index] == 2
            canvas.drawCircle(startX,startY,radius,paint);
        }else if (data[index] == 3){
            canvas.drawCircle(startX,startY,radius,paint);
            startX = index*cellWidth+unitWidth-2.5f*radius;//data[index] == 3
            canvas.drawCircle(startX,startY,radius,paint);
            startX = index*cellWidth+unitWidth+2.5f*radius;//data[index] == 3
            canvas.drawCircle(startX,startY,radius,paint);
        }else {
            canvas.drawCircle(startX,startY,radius,paint);
        }
    }

    private boolean isIllegalIndex(int i){
        return i < 0 || i >= 7;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                int location = getIndexByXY(x,y);
                if (location != selectCurIndex){
                    selectCurIndex = location;
                    setSelected(!mSelected);
                    if (listener != null){
                        listener.onClick(location,dayText.get(location),weekText.get(location));
                    }
                }
                break;
        }
        return true;
    }

    private int getIndexByXY(float x, float y) {
        if (y >= 0 && y <= cellHeight){
            return (int) (x/cellWidth);
        }
        return 0;
    }

    public void setMode(int i){
        if (i >=0 && i <= 5){
            mMode = i;
            initData();
            invalidate();
        }
    }

    public void setDotColor(int color){
        mDotColorString = color;
        invalidate();
    }

    public void setData(int[] data){
        this.data = data;
        invalidate();
    }

    //点击事件
    public interface OnItemClickListener {
        void onClick(int position,String day,String date);
    }

    private WeekDisplay.OnItemClickListener listener;

    public void setOnItemClickListener(WeekDisplay.OnItemClickListener listener) {
        this.listener = listener;
    }
}
