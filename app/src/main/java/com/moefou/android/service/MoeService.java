package com.moefou.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.IBinder;

import com.moefou.android.Application;
import com.moefou.android.core.FmManager;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.PlayMusicEvent;
import com.moefou.android.object.fm.PlayList;
import com.moefou.android.provider.MoeTables;

public class MoeService extends Service {

    private MusicChangeObserver mObserver;

    public static final String OPERATION = "operation";

    @Override
    public void onCreate() {
        super.onCreate();
        mObserver = new MusicChangeObserver(getApplicationContext(), new Handler());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int operationType = 0;
        if (null != intent) {
            operationType = intent.getIntExtra(OPERATION, 0);
        }
        switch (operationType) {
            case 1:
                registerCursorObserver();
                break;

            default:
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        unRegisterCursorObserver();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void registerCursorObserver() {
        Application.getInstance().getContentResolver().registerContentObserver(MoeTables.TPlaylist.CONTENT_URI, true, mObserver);
    }

    private void unRegisterCursorObserver() {
        Application.getInstance().getContentResolver().unregisterContentObserver(mObserver);
    }

    private class MusicChangeObserver extends ContentObserver {
        public MusicChangeObserver(Context context, Handler handler) {
            super(handler);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);

            PlayList playList = FmManager.getInstance().queryOnePlayList();

            if (null == playList) {
                return;
            }

            BusProvider.getInstance().post(new PlayMusicEvent(playList));
//            Application.getInstance().getPlayer().playUrl(playList.getUrl());
        }
    }
}
