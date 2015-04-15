package com.moefou.android.core;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.moefou.android.Application;
import com.moefou.android.Const;
import com.moefou.android.object.fm.FmCover;
import com.moefou.android.object.fm.PlayList;
import com.moefou.android.provider.MoeProvider;
import com.moefou.android.provider.MoeTables;
import com.moefou.android.util.SharedPreferenceUtil;

import java.util.List;

public class FmManager {

    private static final String CURRENT_PLAYLIST_ID = "CURRENT_PLAYLIST_ID";

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

    public PlayList queryOnePlayList() {
        long id = SharedPreferenceUtil.getLong(Const.USER_INFO_FILE, CURRENT_PLAYLIST_ID, -1);
        String where = null;
        if (id > 0) {
            id = ++id;
            where = MoeTables.TPlaylistJoinTFmCover.ID + " = " + id;
        }
        PlayList playList = fetchFirstPlaylist(where);
        if (null != playList) {
            return playList;
        }
        return fetchFirstPlaylist(null);
    }

    private PlayList fetchFirstPlaylist(String where) {
        Cursor cursor = null;
        try {
            cursor = resolver.query(MoeTables.TPlaylistJoinTFmCover.CONTENT_URI_WIKI_JOIN_COVER, null, where, null, null);
            if (null != cursor && cursor.moveToFirst()) {
                PlayList playList = new PlayList(cursor);
                FmCover fmCover = new FmCover(cursor);
                playList.setCover(fmCover);
                SharedPreferenceUtil.saveLong(Const.USER_INFO_FILE, CURRENT_PLAYLIST_ID, playList.getId());
                return playList;
            }
        } catch (Exception e) {

        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return null;
    }
}
