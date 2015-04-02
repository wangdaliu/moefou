package com.meofou.android;


public class Application extends android.app.Application {
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    public Application() {
        Application.instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
