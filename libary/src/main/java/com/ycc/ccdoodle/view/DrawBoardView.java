package com.ycc.ccdoodle.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ycc.ccdoodle.Common.LogUtil;
import com.ycc.ccdoodle.GraffitiLine;
import com.ycc.ccdoodle.R;

import java.util.Date;

/**
 * View组件, 涂鸦画板.
 */
public class DrawBoardView extends View {

    private static final String TAG = DrawBoardView.class.getSimpleName();
    private GraffitiLine mCurrentLine;
    private Object lineDataClock = new Object();
    private Canvas mPaintCanvas;
    private Bitmap mDrawBit;
    private int mPreWidth;

    public DrawBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawBoardView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.lineDataClock = new Object();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean ret = super.onTouchEvent(event);
        float eventX = event.getX();
        float eventY = event.getY();
        //保存一次绘制操作所产生的坐标点
        if (mCurrentLine != null) {
            mCurrentLine.addPoint(eventX, eventY);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ret = true;
                if (mCurrentLine == null) {
                    mCurrentLine = new GraffitiLine(eventX, eventY);
                    mCurrentLine.setLineColor(getResources().getColor(R.color.black));
                }

                break;
            case MotionEvent.ACTION_MOVE:
                ret = true;
                if (mCurrentLine != null) {
                    mCurrentLine.move(eventX, eventY);
                    mCurrentLine.draw(mPaintCanvas);
                }
                this.invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mCurrentLine.endX = eventX;
                mCurrentLine.endY = eventY;
                mCurrentLine.setLineDate(new Date().getTime());
                mCurrentLine = null;
                ret = false;
                break;
            default:
                break;
        }
        return ret;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.d(TAG, "onMeasure " + mDrawBit);
        mDrawBit = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mPaintCanvas = new Canvas(mDrawBit);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.d(TAG, "onLayout " + changed + ", " + left + ", " + top + ", " + right + ", " + bottom);
        mPreWidth = right;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制本地涂鸦时
        if (mDrawBit != null) {
            //实时显示出来
            canvas.drawBitmap(mDrawBit, 0, 0, null);
        }
    }
}
