package com.test.musicplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SPUtil {

    /*** 保存在手机里的SP文件名 */
    private static final String FILE_NAME = "MusicPlayer_sp";

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    private static void initSP(Context context) {
        if (null == sp) {
            sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        }
        if (null == editor) {
            editor = sp.edit();
        }
    }

    /**
     * 往SharedPreferences中保存数据
     */
    public static void put(Context context, String key, Object obj) {

        initSP(context);

        if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj);
        } else if (obj instanceof Float) {
            editor.putFloat(key, (Float) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(key, (Integer) obj);
        } else if (obj instanceof Long) {
            editor.putLong(key, (Long) obj);
        } else {
            editor.putString(key, (String) obj);
        }
        editor.commit();
    }


    /**
     * 从SharedPreferences中获取指定数据
     */
    public static Object get(Context context, String key, Object defaultObj) {
        initSP(context);
        if (defaultObj instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObj);
        } else if (defaultObj instanceof Float) {
            return sp.getFloat(key, (Float) defaultObj);
        } else if (defaultObj instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObj);
        } else if (defaultObj instanceof Long) {
            return sp.getLong(key, (Long) defaultObj);
        } else if (defaultObj instanceof String) {
            return sp.getString(key, (String) defaultObj);
        }
        return null;
    }

    /**
     * 从SharedPreferences删除指定数据
     */
    public static void remove(Context context, String key) {
        initSP(context);
        editor.remove(key);
        editor.commit();
    }


    /**
     * 从SharedPreferences中返回所有键值对
     */
    public static Map<String, ?> getAll(Context context) {
        initSP(context);
        Map<String, ?> map = sp.getAll();
        return map;
    }

    /**
     * 从SharedPreferences中删除所有数据
     */
    public static void clear(Context context) {
        initSP(context);
        editor.clear();
        editor.commit();
    }

    /**
     * 从SharedPreferences中检查key对应的数据是否存在
     */
    public static boolean contains(Context context, String key) {
        initSP(context);
        return sp.contains(key);
    }
}
