package com.fzq.musicplayer.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by fzq on 2017/7/23.
 * 自定义ViewPager是为了解决在HorizontalScrollView中包含ViewPager的滑动冲突问题
 */

public class MyViewPagerOut extends ViewPager {


    float x_down1 = 0;
    float x_down2 = 0;


    public MyViewPagerOut(Context context) {
        super(context);
    }

    public MyViewPagerOut(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

// 内部和外部的ViewPager嵌套 滑动问题还有点小的bug，这里待优化
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                break;
//
//        }
//        return true;
//    }



    /////////////////////////////////////////////////////////////////////////
    //在ViewPager中有两种方式解决滑动冲突的问题

    //下面的onTouchEvent方法也可以实现这个效果
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x_down1 = ev.getX();

                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_MOVE:
                float move_x = x_down1 - ev.getX();
                if (getCurrentItem() == 0) {
                    if (move_x < 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else if (move_x > 0) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;

            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    //上面的dispatchTouchEvent方法也可以实现相同的效果
    // @Override
    // public boolean onTouchEvent(MotionEvent ev) {
    // int action = ev.getAction();
    // switch (action) {
    // case MotionEvent.ACTION_DOWN:
    // x_down = ev.getX();
    // break;
    // case MotionEvent.ACTION_MOVE:
    //
    // float move_x = x_down - ev.getX();
    // if (getCurrentItem() == 0) {
    // if (move_x < 0) {
    // getParent().requestDisallowInterceptTouchEvent(false);
    // } else if (move_x > 0) {
    // getParent().requestDisallowInterceptTouchEvent(true);
    // }
    // } else {
    // getParent().requestDisallowInterceptTouchEvent(true);
    // }
    //
    // break;
    //
    // }
    //
    // return super.onTouchEvent(ev);
    // }


}
