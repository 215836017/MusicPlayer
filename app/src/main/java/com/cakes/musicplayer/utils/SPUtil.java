package com.cakes.musicplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class SPUtil {

    /*** name of SharedPreferences */
    private static final String FILE_NAME = "musicPlayerSP";

    private static SPUtil INSTANCE;

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    private SPUtil() {
    }

    public static SPUtil getInstance() {
        if (null == INSTANCE) {
            synchronized (SPUtil.class) {
                if (null == INSTANCE) {
                    INSTANCE = new SPUtil();
                }
            }
        }
        return INSTANCE;
    }

    public void initSP(Context context) {
        if (null == sp) {
            sp = context.getApplicationContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        }
        if (null == editor) {
            editor = sp.edit();
        }
    }

/*
SharedPreference中editor.apply()和editor.commit()的区别:
相同点：
1.二者都可提交preference的修改数据
2.二者都是原子操作

区别：
1.apply没有返回值而commit返回boolean表明修改是否提交成功
2.apply是将修改数据原子提交到内存，而后异步真正提交到硬件磁盘；而commit是同步的提交到硬件磁盘，因此，在多个
并发的提交commit的时候，他们会等待正在处理的commit保存到磁盘后在操作，从而降低了效率。而apply只是原子的提交
到内容，后面有调用apply的函数的将会直接覆盖前面的内存数据，这样从一定程度上提高了很多效率。
3.apply方法不会提示任何失败的提示。

总结:
由于在一个进程中，sharedPreference是单实例，一般不会出现并发冲突，如果对提交的结果不关心的话，建议使用apply，
当然需要确保提交成功且有后续操作的话，还是需要用commit的。
 */

    /**
     * save int value to SharedPreferences
     */
    public void putInt(String key, int value) {
        editor.putInt(key, value).commit();
    }

    /**
     * save String value to SharedPreferences
     */
    public void putString(String key, String value) {
        editor.putString(key, value).commit();
    }

    /**
     * get int value from SharedPreferences
     */
    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    /**
     * delete the Value of Key from SharedPreferences
     */
    public void remove(Context context, String key) {
        editor.remove(key);
        editor.commit();
    }


    /**
     * return all Key-Values from SharedPreferences
     */
    public Map<String, ?> getAll(Context context) {
        Map<String, ?> map = sp.getAll();
        return map;
    }

    /**
     * delete Value of Key from SharedPreferences
     */
    public void clear(Context context) {
        initSP(context);
        editor.clear();
        editor.commit();
    }

    /**
     * check the Value fo Key is exist in SharedPreferences
     */
    public static boolean contains(Context context, String key) {
        return sp.contains(key);
    }
}
