package com.moefou.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.moefou.android.Application;

public class SharedPreferenceUtil {

    public static void clear(String fileName) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().apply();
    }

    public static void remove(String fileName, String key) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key).apply();
    }

    public static void save(String fileName, String key, String value) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value).apply();
    }

    public static String getValue(String fileName, String key) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public static void saveInt(String fileName, String key, int value) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value).apply();
    }

    public static int getInt(String fileName, String key, int defaultValue) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static void saveLong(String fileName, String key, long value) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value).apply();
    }

    public static long getLong(String fileName, String key, long defaultValue) {
        SharedPreferences sp = Application.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }
}
