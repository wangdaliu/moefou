package com.moefou.android.core;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.moefou.android.Application;
import com.moefou.android.object.fm.PlayList;
import com.moefou.android.provider.MoeProvider;
import com.moefou.android.provider.MoeTables;

import java.util.List;

public class FmManager {

    private static FmManager mFmManager = new FmManager();

    private ContentResolver resolver = Application.getInstance().getContentResolver();

    public static FmManager getInstance() {
        return mFmManager;
    }

    public void deletePlayLists() {
        resolver.delete(MoeTables.TPlaylist.CONTENT_URI, null, null);
    }

    public void savePlayLists(List<PlayList> playLists) {
        SQLiteDatabase db = null;
        try {
            db = MoeProvider.getSQLiteOpenHelper().getWritableDatabase();
            db.beginTransaction();
            for (PlayList playList : playLists) {
                savePlayList(playList);
            }
        } catch (Exception e) {

        } finally {
            if (null != db) {
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        }
    }

    private void savePlayList(PlayList playList) {
        // save user
        Uri uri = resolver.insert(MoeTables.TPlaylist.CONTENT_URI, playList.toContentValues());
        // save icon
        long id = Long.parseLong(uri.getLastPathSegment());
        playList.getCover().setFkFmId(id);
        resolver.insert(MoeTables.TFmCover.CONTENT_URI, playList.getCover().toContentValues());
    }

}
