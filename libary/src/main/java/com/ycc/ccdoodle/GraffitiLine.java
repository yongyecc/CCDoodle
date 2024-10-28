package com.ycc.ccdoodle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;

import org.json.JSONArray;
import org.json.JSONObject;

public class GraffitiLine {

    public static final float RADIUS = 80;
    public static final int DEFAULT_STROKE_WIDTH = 15;
    public float endX;
    public float endY;
    public float startX;
    public float startY;
    public Path mPath;
    public JSONObject mPoints;  //发送给对端的坐标点

    private long mLineDate;

    private static int mLocalBrushColor = 0x80DBFF; // 画笔颜色
    private static int mLocalStrokeWidth = DEFAULT_STROKE_WIDTH; // 画笔粗细
    private int mLineColor;     //线条颜色
    private int mLineWidth = 5;   //线条粗细

    public int getLineColor() {
        return mLineColor;
    }

    public void setLineColor(int lineColor) {
        this.mLineColor = lineColor;
    }

    public int getLineWidth() {
        return mLineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.mLineWidth = lineWidth;
    }

    public Path getPath() {
        //本地绘制时用到的路径
        return mPath;
    }

    public long getLineDate() {
        return mLineDate;
    }

    public void setLineDate(long lineDate) {
        mLineDate = lineDate;
    }

    public JSONObject getmPoints() {
        return mPoints;
    }

    public static void setBrushColor(int brushColor) {
        mLocalBrushColor = brushColor;
    }

    public static int getBrushColor() {
        return mLocalBrushColor;
    }

    public static void setBrushWidth(int brushColor) {
        mLocalStrokeWidth = brushColor;
    }

    public static int getBrushWidth() {
        return mLocalStrokeWidth;
    }

    /**
     *  添加坐标点.
     * @param pointX    .
     * @param pointY    .
     */
    public void addPoint(float pointX, float pointY) {
        try {
            if (this.mPoints == null) {
                this.mPoints = new JSONObject();
                this.mPoints.put("x", new JSONArray());
                this.mPoints.put("y", new JSONArray());
            }
            ((JSONArray)(this.mPoints.get("x"))).put(pointX);
            ((JSONArray)(this.mPoints.get("y"))).put(pointY);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 构造方法 .
     *
     * @param pointX pointx
     * @param pointY pointY
     */
    public GraffitiLine(float pointX, float pointY) {
        super();
        this.startX = pointX;
        this.startY = pointY;
        this.mPath = new Path();
        mPath.moveTo(pointX, pointY);
        mPath.lineTo(pointX, pointY);
        setLineColor(getBrushColor());
        setLineWidth(getBrushWidth());
    }

    /**
     * 绘制本端的线条 .
     *
     * @param canvas 画板
     */
    public void draw(Canvas canvas) {
        Paint paint = getPaint();
        canvas.drawPath(mPath, paint);
    }

    /**
     * 移动 .
     *
     * @param pointX pointX
     * @param pointY pointY
     */
    public void move(float pointX, float pointY) {
        mPath.lineTo(pointX, pointY);
    }

    /**
     * 获取画笔 .
     *
     * @return paint .
     */
    public Paint getPaint() {
        Paint paint = new Paint();
        paint.setColor(getLineColor());
        paint.setAntiAlias(true);
        paint.setStrokeWidth(getLineWidth());
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setPathEffect(new CornerPathEffect(RADIUS));
        return paint;
    }

    public String toString() {
        return "{startX = " + this.startX + ", startY = " + this.startY + "}";
    }
}
