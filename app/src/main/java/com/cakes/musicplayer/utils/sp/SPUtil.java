package com.cakes.musicplayer.utils.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SPUtil {

    public static void putString(Context context, final String key,
                                 final String value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putString(key, value).commit();
    }

    public static void putStringWithApply(Context context, final String key,
                                          final String value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key,
                                   final String defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getString(key, defaultValue);
    }

    public static void putBoolean(Context context, final String key,
                                  final boolean value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putBoolean(key, value).commit();
    }

    public static void putBooleanWithApply(Context context, final String key,
                                           final boolean value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, final String key,
                                     final boolean defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getBoolean(key, defaultValue);
    }


    public static void putInt(Context context, final String key,
                              final int value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putInt(key, value).commit();
    }

    public static void putIntWithApply(Context context, final String key,
                                       final int value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, final String key,
                             final int defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getInt(key, defaultValue);
    }

    public static void putFloat(Context context, final String key,
                                final float value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putFloat(key, value).commit();
    }

    public static void putFloatWithApply(Context context, final String key,
                                         final float value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context, final String key,
                                 final float defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getFloat(key, defaultValue);
    }

    public static void putLong(Context context, final String key,
                               final long value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putLong(key, value).commit();
    }

    public static void putLongWithApply(Context context, final String key,
                                        final long value) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, final String key,
                               final long defaultValue) {
        final SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getLong(key, defaultValue);
    }

    public static boolean hasKey(Context context, final String key) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).contains(
                key);
    }

    public static void clear(Context context,
                             final SharedPreferences p) {
        final SharedPreferences.Editor editor = p.edit();
        editor.clear();
        editor.apply();
    }
}
