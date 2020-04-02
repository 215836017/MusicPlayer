package com.cakes.musicplayer.utils;

import android.util.Log;

public class LogUtil {

    private static boolean open = true;

    public static void isOpen(boolean isOpen) {
        open = isOpen;
    }

    private static final String LOG_FLAG = "musicPlayer_log ";

    public static void d(String tag, String msg) {
//        if (open) {
//            Log.d(tag, LOG_FLAG + msg);
//        }

        showLog(tag, LOG_FLAG + msg);
    }

    public static void w(String tag, String msg) {
//        if (open) {
//            Log.w(tag, LOG_FLAG + msg);
//        }

        showLog(tag, LOG_FLAG + msg);
    }

    public static void e(String tag, String msg) {
//        if (open) {
//            Log.e(tag, LOG_FLAG + msg);
//        }

        showLog(tag, LOG_FLAG + msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
//        if (open) {
//            Log.e(tag, LOG_FLAG + msg, tr);
//        }


        showLog(tag, LOG_FLAG + msg);
    }


    public static void i(String tag, String msg) {
//        if (open) {
//            Log.i(tag, LOG_FLAG + msg);
//        }

        showLog(tag, LOG_FLAG + msg);
    }


    public static void v(String tag, String msg) {
//        if (open) {
//            Log.v(tag, LOG_FLAG + msg);
//
//        }

        showLog(tag, LOG_FLAG + msg);
    }

    private static int log_count = 0;
    private static final int LEVEL_D = 1;
    private static final int LEVEL_W = 2;
    private static final int LEVEL_E = 3;
    private static final int LEVEL_I = 4;
    private static final int LEVEL_V = 5;

    private static final int LINE = 2;

    private static void showLog(String tag, String msg) {
        log_count++;
        if (log_count <= LEVEL_D * LINE) {
            Log.d(tag, msg);

        } else if (log_count <= LEVEL_W * LINE) {
            Log.w(tag, msg);

        } else if (log_count <= LEVEL_E * LINE) {
            Log.e(tag, msg);

        } else if (log_count <= LEVEL_I * LINE) {
            Log.i(tag, msg);

        } else if (log_count < LEVEL_V * LINE) {
            Log.v(tag, msg);

        }else if(log_count == LEVEL_V * LINE){
            Log.v(tag, msg);
            log_count = 1;
        }
    }
}