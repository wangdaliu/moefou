package com.meofou.android.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.meofou.android.Application;

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
}