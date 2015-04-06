package com.moefou.android;


import com.activeandroid.ActiveAndroid;

public class Application extends android.app.Application {
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ActiveAndroid.initialize(this);
    }
}
