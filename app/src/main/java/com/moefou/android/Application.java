package com.moefou.android;

import android.content.Intent;

import com.moefou.android.core.Player;
import com.moefou.android.service.MoeService;

public class Application extends android.app.Application {
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    private Player mPlayer = new Player();

    public Player getPlayer() {
        return mPlayer;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        startService(new Intent(this, MoeService.class));
    }
}
