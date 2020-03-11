package com.example.yanfa.util;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import com.example.yanfa.bean.ExamineInfo;
import com.orient.me.utils.UIUtils;
import com.orient.me.widget.rv.itemdocration.timeline.DoubleTimeLineDecoration;

public class ExamineInfoDTL extends DoubleTimeLineDecoration {
    private int r;
    private Paint monTextPaint;
    private Paint dayTextPaint;
    private int space;

    public ExamineInfoDTL(Config config) {
        super(config);
    }

    @Override
    protected void onDrawTitleItem(Canvas canvas, int left, int top, int right, int bottom, int centerX, int pos, boolean isLeft) {
        // 不需要做什么
    }
    @Override
    protected void onDrawDotResItem(Canvas canvas, int cx, int cy, int radius, Drawable drawable, int pos) {
        super.onDrawDotResItem(canvas, cx, cy, radius, drawable, pos);
        int width = drawable.getIntrinsicWidth();
        mDotPaint.setStyle(Paint.Style.FILL);
        ExamineInfo timeItem =  (ExamineInfo) timeItems.get(pos);
        mDotPaint.setColor(timeItem.getColor());
        canvas.drawCircle(cx,cy,width / 2 - UIUtils.dip2px(2),mDotPaint);
        if (drawable != null) {
            int height = drawable.getIntrinsicHeight();
            int left = cx - width / 2;
            int top = cy - height / 2;
            int right = cx + width / 2;
            int bottom = cy + height / 2;
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
            mDotPaint.setStyle(Paint.Style.STROKE);
            mDotPaint.setColor(Color.parseColor("#CDC9C9"));
            mDotPaint.setStrokeWidth(UIUtils.dip2px(2));
            canvas.drawCircle(cx, cy, width / 2 - UIUtils.dip2px(3), mDotPaint);
        }
    }
//    @Override
//    protected void onDrawDotItem(Canvas canvas, int cx, int cy, int radius, int pos) {
//        super.onDrawDotItem(canvas, cx, cy, radius, pos);
//        ExamineInfo timeItem = (ExamineInfo) timeItems.get(pos);
//        mDotPaint.setColor(timeItem.getColor());
//        canvas.drawCircle(cx,cy,r,mDotPaint);
//
//    }
}
