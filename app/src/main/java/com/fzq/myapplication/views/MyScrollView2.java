package com.fzq.myapplication.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by fzq on 2017/7/12.
 */

public class MyViewPager2 extends HorizontalScrollView {
    public MyViewPager2(Context context) {
        this(context, null);
    }
    public MyViewPager2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }




    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
