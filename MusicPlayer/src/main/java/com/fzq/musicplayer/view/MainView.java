package com.fzq.musicplayer.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.fzq.musicplayer.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by fzq on 2017/6/25.
 */

public class MainView extends HorizontalScrollView {

    /**
     * 因为HorizontalScrollView里面要求只能包含一个控件，所以，里面的根控件用LinearLayout
     */
    private LinearLayout linearLayout;
    /**
     * 左侧的菜单栏
     */
    private ViewGroup menuView;
    /**
     * 右侧的内容栏
     */
    private ViewGroup contentView;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;
    /**
     * 菜单栏的宽度
     */
    private int menuWidth;
    /**
     * 菜单栏距离屏幕右边距
     */
    private int menuRightPadding;

    private boolean once = false;

    public MainView(Context context) {
        this(context, null);
    }

    /**
     * 未使用自定义属性时，调用
     *
     * @param context
     * @param attrs
     */
    public MainView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 当使用了自定义属性时，会调用此构造方法
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.mainViewAttr, defStyleAttr, 0);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.mainViewAttr_rightPadding) {
                menuRightPadding = typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
                        context.getResources().getDisplayMetrics()));

                break;
            }
        }
        typedArray.recycle();

        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
      //  DisplayMetrics outMetrics = context.getResources().getDisplayMetrics();
        DisplayMetrics outMertics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMertics);
        screenWidth = outMertics.widthPixels;

    }

    /**
     * 第一步：测量
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if(!once){
            //先拿到对应的控件
            linearLayout = (LinearLayout) getChildAt(0);
            //是水平布局，菜单栏在左侧
            menuView = (ViewGroup) linearLayout.getChildAt(0);
            contentView = (ViewGroup) linearLayout.getChildAt(1);

            menuWidth = menuView.getLayoutParams().width = screenWidth - menuRightPadding;
            int w = contentView.getLayoutParams().width = screenWidth;

            System.out.println("onMeasure -- menuWidth: " + menuWidth + "contentWidth: " + w);
            once = true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 第二步：定位
     * 通过设置偏移量，将menu隐藏
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(menuWidth, 0);
        }
    }

    /**
     * 第三步：绘画，交给父控件HorizontalScrollView
     */

    /**
     * 第四步：处理其他的事件触发情况，在这里比如就是触摸是滚动事件
     */

    private boolean isOpen = false;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                System.out.println("onTouchEvent -- scrollX: " + scrollX);
                if(scrollX >= 200){
                    smoothScrollTo(menuWidth, 0);
                    isOpen = false;
                }else{

                    smoothScrollTo(0, 0);
                    isOpen = true;
                }
                break;
//            case MotionEvent.ACTION_MOVE:
//                //滑动事件交给父控件HorizontalScrollView
//                break;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 滚动发生时，添加动画或特效
     * 区别1：内容区域1.0~0.7 缩放的效果 scale : 1.0~0.0 0.7 + 0.3 * scale
     * 区别2：菜单的偏移量需要修改
     * 区别3：菜单的显示时有缩放以及透明度变化 缩放：0.7 ~1.0 1.0 - scale * 0.3 透明度 0.6 ~ 1.0
     * 0.6+ 0.4 * (1- scale) ;
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    /*
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / menuWidth; // 1 ~ 0， 比例值
        /**
         * 区别1：内容区域1.0~0.7 缩放的效果 scale : 1.0~0.0 0.7 + 0.3 * scale
         *
         * 区别2：菜单的偏移量需要修改
         *
         * 区别3：菜单的显示时有缩放以及透明度变化 缩放：0.7 ~1.0 1.0 - scale * 0.3 透明度 0.6 ~ 1.0
         * 0.6+ 0.4 * (1- scale) ;
         *
         */
    /*
        float rightScale = 0.7f + 0.3f * scale;
        float leftScale = 1.0f - scale * 0.3f;
        float leftAlpha = 0.6f + 0.4f * (1 - scale);

        // 调用属性动画，设置TranslationX
        ViewHelper.setRotationX(menuView, menuWidth * scale * 0.8f);
        ViewHelper.setScaleX(menuView, leftScale);
        ViewHelper.setScaleY(menuView, rightScale);
        ViewHelper.setAlpha(menuView, leftAlpha);

        // 设置content的缩放的中心点
        ViewHelper.setPivotX(contentView, 0);
        ViewHelper.setPivotY(contentView, contentView.getHeight() / 2);
        ViewHelper.setScaleX(contentView, rightScale);
        ViewHelper.setScaleY(contentView, rightScale);
    }

*/
    public void toggle(){
        if(isOpen){
            closeMenu();
        }else{
            openMenu();
        }
    }
    /**
     * 打开菜单
     */
    public void openMenu()
    {
        if (isOpen)
            return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu()
    {
        if (!isOpen)
            return;
        this.smoothScrollTo(menuWidth, 0);
        isOpen = false;
    }
}
