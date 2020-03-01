package com.example.yanfa.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

/**
 * 一个点击会有简单效果的cardView
 *
 */
public class NewCardView extends CardView {

    public NewCardView(@NonNull Context context) {
        super(context);
    }

    public NewCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //点击事件到来的时候进行判断处理
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction()==MotionEvent.ACTION_DOWN) {
            clickEvent();
        }

        if (ev.getAction()==MotionEvent.ACTION_UP){
            upEvent();
        }
        return super.dispatchTouchEvent(ev);
    }


    //viewGroup本身不会处理事件，所以这里要返回true
    //若内部为clickable的控件事件不会交给cardView处理导致up事件不能触发
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


    //手指按下的时候触发的事件;大小高度变小，透明度减少
    private void clickEvent(){
        setCardElevation(4);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(this,"scaleX",1,0.97f),
                ObjectAnimator.ofFloat(this,"scaleY",1,0.97f),
                ObjectAnimator.ofFloat(this,"alpha",1,0.9f)
        );
        set.setDuration(100).start();
    }


    //手指抬起的时候触发的事件；大小高度恢复，透明度恢复
    private void upEvent(){
        setCardElevation(getCardElevation());
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(this,"scaleX",0.97f,1),
                ObjectAnimator.ofFloat(this,"scaleY",0.97f,1),
                ObjectAnimator.ofFloat(this,"alpha",0.9f,1)
        );
        set.setDuration(100).start();
    }

}
