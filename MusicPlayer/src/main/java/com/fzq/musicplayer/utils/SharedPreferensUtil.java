package com.fzq.musicplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.fzq.musicplayer.Constant;

public class SharedPreferensUtil {


    private static SharedPreferences sp = null;
    private String spName;

    public static SharedPreferences getInstance(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (null == sp) {
            synchronized (SharedPreferensUtil.class) {
                if (null == sp) {
                    sp = context.getSharedPreferences(Constant.spName, applicationContext.MODE_PRIVATE);
                }
            }
        }
        return sp;
    }

}

