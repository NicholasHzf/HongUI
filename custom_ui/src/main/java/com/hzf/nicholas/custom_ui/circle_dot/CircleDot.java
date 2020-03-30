package com.hzf.nicholas.custom_ui.circle_dot;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.hzf.nicholas.custom_ui.R;
import com.hzf.nicholas.custom_ui.util.RenderUtil;

import androidx.annotation.Nullable;

public class CircleDot extends View {

    private Paint mPaint;
    private Paint mStrokePaint;
    private String mColorStringInner;
    private String mColorStringStroke;
    private Context mContext;
    private boolean ableSelect;
    private boolean selected;
    private float radius;
    private float strokeRatio;

    public CircleDot(Context context) {
        this(context,null,0);
    }

    public CircleDot(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleDot(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleDot);
        if (typedArray != null) {
            mColorStringInner = typedArray.getString(R.styleable.CircleDot_inner_color);
            mColorStringStroke = typedArray.getString(R.styleable.CircleDot_stroke_color);
            ableSelect = typedArray.getBoolean(R.styleable.CircleDot_able_select,false);
            selected = typedArray.getBoolean(R.styleable.CircleDot_selected,false);
            radius = typedArray.getFloat(R.styleable.CircleDot_radius,12);
            strokeRatio = typedArray.getFloat(R.styleable.CircleDot_stroke_ratio,0.02f);
            typedArray.recycle();
        }

        init();
    }

    private void init() {
        if (mColorStringInner == null){
            mColorStringInner = "#ff0000";
        }
        if (mColorStringStroke == null){
            mColorStringStroke = "#0000ff";
        }
        if (strokeRatio > 1){
            strokeRatio = 0.02f;
        }

        mPaint = RenderUtil.getPaint(Color.parseColor(mColorStringInner));
        mPaint.setStyle(Paint.Style.FILL);
        mStrokePaint = RenderUtil.getPaint(Color.parseColor(mColorStringStroke));
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(radius*strokeRatio);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        radius = Math.min(getMeasuredWidth(), getMeasuredHeight())/2f;
        mStrokePaint.setStrokeWidth(radius*strokeRatio);
        float x = getMeasuredWidth()/2f;
        float y = getMeasuredHeight()/2f;
        if (ableSelect){
            if (selected){
                canvas.drawCircle(x,y,radius*(1-strokeRatio),mPaint);
                canvas.drawCircle(x,y,radius*(1-strokeRatio/2),mStrokePaint);
            }else {
                canvas.drawCircle(x,y,radius*(1-strokeRatio),mPaint);
            }
        }else {
            canvas.drawCircle(x,y,radius,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (ableSelect){
                    if (selected){
                        selected = false;
                    }else {
                        if (listener != null){
                            listener.onClick();
                        }
                        selected = true;
                    }
                    invalidate();
                }else {
                    if (listener != null){
                        listener.onClick();
                    }
                }
                break;
        }
        return true;
    }

    //点击事件
    public interface OnClickListener {
        void onClick();
    }

    private CircleDot.OnClickListener listener;

    public void setOnClickListener(CircleDot.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) (radius*2);
        int height = (int) (radius*2);

        // get calculate mode of width and height
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        // get recommend width and height
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

    public void setColorStringInner(String color){
        mColorStringInner = color;
        init();
        invalidate();
    }

    public void setColorStringStroke(String color){
        mColorStringStroke = color;
        init();
        invalidate();
    }

    public void setRadius(float radius){
        this.radius = radius;
        init();
        invalidate();
    }

    public void setAbleSelect(boolean ableSelect){
        this.ableSelect = ableSelect;
        init();
        invalidate();
    }

    public void setSelected(boolean selected){
        this.selected = selected;
        init();
        invalidate();
    }

    public void setStrokeRatio(float strokeRatio){
        this.strokeRatio = strokeRatio;
        init();
        invalidate();
    }
}
