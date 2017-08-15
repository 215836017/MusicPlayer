package com.fzq.musicplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fzq.musicplayer.Constant;

public class SharedPreferensUtil {


    private static SharedPreferences sp = null;
    private String spName;

    public static SharedPreferences getInstance(Context context) {
        if (null == sp) {
            synchronized (SharedPreferensUtil.class) {
                if (null == sp) {
                    sp = context.getSharedPreferences(Constant.spName, context.MODE_PRIVATE);
                }
            }

        }
        return sp;
    }

    /**
     * 如果在Application或BaseActivity中初始化SP了，那么任何一个类如果要用的话就在这里直接返回好了。
     *
     * @return
     */
    public static SharedPreferences getInstance() {

        return sp;
    }


}

