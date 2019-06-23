package com.test.musicplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 参考链接：
 * 1. https://www.jianshu.com/p/1a931d943fb4
 */
public class ScreenUtil {

    // TODO   https://www.jianshu.com/p/1a931d943fb4

    public static int[] getScreenWidthAndHeight(Activity activity) {
        int[] wh = new int[2];
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        wh[0] = outMetrics.widthPixels;
        wh[1] = outMetrics.heightPixels;

        return wh;
    }
}
