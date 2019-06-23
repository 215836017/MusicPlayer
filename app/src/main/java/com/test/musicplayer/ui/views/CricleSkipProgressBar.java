package com.test.musicplayer.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class CricleSkipProgressBar extends View {

    private Paint paint;

    private int roundWidth;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private int textSize;

    private int maxProgress;
    private int currentProgress;
    public static final int STROKE = 0;
    private boolean isStrartAnim = false;

    private AnimThread animThread;
    private int timeSecond;
    private int sleepCount = 1;
    private final int sleepTime = 100;  //1秒10帧
    private final int range = 1000 / sleepTime;

    private OnProgressAnimListener listener;

    public CricleSkipProgressBar(Context context) {
        this(context, null);
    }

    public CricleSkipProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CricleSkipProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    private void init() {
        paint = new Paint();
        roundColor = 0xfffdba14;
        roundProgressColor = 0xfffc8b0d;
        textColor = 0xff00ff00;
        textSize = 15;
        roundWidth = 5;
        maxProgress = 100;

        animThread = new AnimThread();
        sleepCount = 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //1. 画最外层的圆环
        int center = getWidth() / 2;
        int radius = center - roundWidth / 2;
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);  // 设置圆环的宽度
        paint.setAntiAlias(true); //消除锯齿
        canvas.drawCircle(center, center, radius, paint);

        //2. 画圆弧，即进度
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundProgressColor);
        RectF oval = new RectF(center - radius, center - radius,
                center + radius, center + radius);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, 0, 360 * currentProgress / maxProgress,
                false, paint);  // 根据进度画圆弧

        //3. 画中心的文字
        paint.setStrokeWidth(0);
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        canvas.drawText("Skip\n " + timeSecond + "s", center, center, paint);
    }

    public synchronized int getMaxProgress() {
        return maxProgress / range;
    }

    public synchronized void setMaxProgress(int max) {
        if (max > 0) {
            this.timeSecond = max;
            this.maxProgress = max * range;
        }
    }

    public void startAnim() {
        isStrartAnim = true;

        animThread.start();
    }

    public void stopAnim() {
        isStrartAnim = false;
    }

    public void setOnProgressAnimListener(OnProgressAnimListener listener) {
        this.listener = listener;
    }

    public interface OnProgressAnimListener {
        void progressAnimFinish();
    }

    class AnimThread extends Thread {
        @Override
        public void run() {
            super.run();

            while (isStrartAnim) {
                try {
                    sleep(sleepTime);
                    sleepCount++;
                    if (sleepTime * sleepCount == 1000) {  //1秒时间
                        if (timeSecond == 0) {
                            listener.progressAnimFinish();
                            break;
                        }
                        timeSecond--;
                    }
                    currentProgress += range;
                } catch (Exception e) {

                }
                postInvalidate();
            }
        }
    }
}
