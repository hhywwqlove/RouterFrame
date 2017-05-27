package com.router.android1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Create by WeiqiangWang on 17/5/12.
 */

public class PosterView extends View {

    private Paint mFramePaint = new Paint();

    private Paint mCirclePaint = new Paint();

    private int frameWidth = 600;

    private int contentWidth = 1200;

    private RectF rectF, rectF2;

    Region re = new Region();


    public PosterView(@NonNull Context context) {
        super(context);
        init();
    }

    public PosterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PosterView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mFramePaint.setColor(Color.RED);
        mFramePaint.setStrokeWidth(10);
        mFramePaint.setStyle(Paint.Style.STROKE);

        mCirclePaint.setColor(Color.BLUE);
        mFramePaint.setStrokeWidth(10);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int left = getWidth() / 2 + getLeft() - frameWidth / 2;
        int right = left + frameWidth;
        int top = (getHeight() - frameWidth) / 2;
        int bottom = top + frameWidth;

        rectF = new RectF(left, top, right, bottom);

        re.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);

        rectF2 = new RectF(rectF.left - (contentWidth - frameWidth) / 2, rectF.top - (contentWidth - frameWidth) / 2
                , rectF.right + (contentWidth - frameWidth) / 2, rectF.bottom + (contentWidth - frameWidth) / 2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.restore();
//        drawCircle(canvas);
        drawContent(canvas);
        drawFrame(canvas);
    }

    /**
     * 在view的中间画一个方形的框
     *
     * @param canvas
     */
    private void drawFrame(Canvas canvas) {

        canvas.drawRect(rectF, mFramePaint);

    }

    private void drawContent(Canvas canvas) {
        canvas.drawRect(rectF2, mCirclePaint);
    }

    private float mPreX;
    private float mPreY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!isContain(event)) {
                return false;
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mPreX = event.getX();
                mPreY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                float mX = event.getX();
                float mY = event.getY();

                float previousX = mPreX;
                float previousY = mPreY;

                float disX = mX - previousX;
                float disY = mY - previousY;

                if (disX > 0) {

                    float left = rectF2.left + Math.abs(disX);
                    if (left <= rectF.left) {
                        rectF2.left = left;
                        rectF2.right = rectF2.right + Math.abs(disX);
                    }

                } else {
                    float right = rectF2.right - Math.abs(disX);
                    if (right >= rectF.right) {
                        rectF2.left = rectF2.left - Math.abs(disX);
                        rectF2.right = right;
                    }
                }

                if (disY > 0) {
                    float top = rectF2.top + Math.abs(disX);
                    if (top <= rectF.top) {
                        rectF2.top = top;
                        rectF2.bottom = rectF2.bottom + Math.abs(disX);
                    }

                } else {
                    float bottom = rectF2.bottom - Math.abs(disX);
                    if (bottom >= rectF.bottom) {
                        rectF2.bottom = bottom;
                        rectF2.top = rectF2.top - Math.abs(disX);
                    }
                }

                mPreX = mX;
                mPreY = mY;
                break;
        }
        invalidate();
        return true;

    }

    private boolean isContain(MotionEvent event) {
        return re.contains((int) event.getX(), (int) event.getY());
    }
}
