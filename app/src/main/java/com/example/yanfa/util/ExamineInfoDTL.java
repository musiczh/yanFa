package com.example.yanfa.util;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

        r = UIUtils.dip2px(10);
        monTextPaint = new Paint();
        monTextPaint.setTextSize(UIUtils.sp2px(mContext,12));
        monTextPaint.setColor(Color.parseColor("#F5F5F5"));

        dayTextPaint = new Paint();
        dayTextPaint.setTextSize(UIUtils.sp2px(mContext,18));
        dayTextPaint.setColor(Color.parseColor("#ffffff"));

        space = UIUtils.dip2px(6);

        mDotPaint.setMaskFilter(new BlurMaskFilter(6, BlurMaskFilter.Blur.SOLID));;

    }

    @Override
    protected void onDrawTitleItem(Canvas canvas, int left, int top, int right, int bottom, int centerX, int pos, boolean isLeft) {
        // 不需要做什么
    }

    @Override
    protected void onDrawDotItem(Canvas canvas, int cx, int cy, int radius, int pos) {
        super.onDrawDotItem(canvas, cx, cy, radius, pos);

        ExamineInfo timeItem = (ExamineInfo) timeItems.get(pos);
//        Date date = timeItem.getDate();
        mDotPaint.setColor(timeItem.getColor());
        canvas.drawCircle(cx,cy,r,mDotPaint);
    }
}
