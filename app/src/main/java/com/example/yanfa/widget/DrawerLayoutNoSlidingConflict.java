package com.example.yanfa.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

/**
 * 解决滑动冲突DrawerLayout控件
 */
public class DrawerLayoutNoSlidingConflict extends DrawerLayout {
    public DrawerLayoutNoSlidingConflict(@NonNull Context context) {
        super(context);
        init();
    }

    public DrawerLayoutNoSlidingConflict(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawerLayoutNoSlidingConflict(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    //初始化模块。获取DrawerLayout中的有效最短滑动距离
    private void init(){
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();

    }



    private int mTouchSlop;
    //保存最后一次的坐标
    private float latestX;
    private float latestY;
    //拦截点击事件；如果超过最短滑动距离而且x轴滑动距离是y轴的4倍时，拦截下滑动事件。
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                latestX = x;
                latestY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int)Math.abs(x-latestX);
                int deltaY = (int)Math.abs(y-latestY);
                boolean isMove = deltaX*deltaX+deltaY*deltaY > mTouchSlop*mTouchSlop;
                if (isMove)
                    return deltaX > 4 * deltaY;
                break;
            default:break;
        }


        return super.onInterceptTouchEvent(ev);
    }

}

