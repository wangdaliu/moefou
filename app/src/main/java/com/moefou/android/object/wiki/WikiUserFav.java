package com.moefou.android.object.wiki;

import android.content.ContentValues;
import android.database.Cursor;

import com.moefou.android.provider.MoeTables.TWikiUserFav;

public class WikiUserFav {

    private long fkWikiId;

    private int fav_id;

    private int fav_obj_id;

    private String fav_obj_type;

    private int fav_uid;

    private long fav_date;

    private int fav_type;

    public long getFkWikiId() {
        return fkWikiId;
    }

    public void setFkWikiId(long fkWikiId) {
        this.fkWikiId = fkWikiId;
    }

    public int getFav_id() {
        return fav_id;
    }

    public void setFav_id(int fav_id) {
        this.fav_id = fav_id;
    }

    public int getFav_obj_id() {
        return fav_obj_id;
    }

    public void setFav_obj_id(int fav_obj_id) {
        this.fav_obj_id = fav_obj_id;
    }

    public String getFav_obj_type() {
        return fav_obj_type;
    }

    public void setFav_obj_type(String fav_obj_type) {
        this.fav_obj_type = fav_obj_type;
    }

    public int getFav_uid() {
        return fav_uid;
    }

    public void setFav_uid(int fav_uid) {
        this.fav_uid = fav_uid;
    }

    public long getFav_date() {
        return fav_date;
    }

    public void setFav_date(long fav_date) {
        this.fav_date = fav_date;
    }

    public int getFav_type() {
        return fav_type;
    }

    public void setFav_type(int fav_type) {
        this.fav_type = fav_type;
    }

    public WikiUserFav() {
    }

    public WikiUserFav(Cursor cursor) {
        this.fkWikiId = cursor.getLong(cursor.getColumnIndex(TWikiUserFav.FK_WIKI));
        this.fav_id = cursor.getInt(cursor.getColumnIndex(TWikiUserFav.FAV_ID));
        this.fav_obj_id = cursor.getInt(cursor.getColumnIndex(TWikiUserFav.FAV_OBJ_ID));
        this.fav_obj_type = cursor.getString(cursor.getColumnIndex(TWikiUserFav.FAV_OBJ_TYPE));
        this.fav_uid = cursor.getInt(cursor.getColumnIndex(TWikiUserFav.FAV_UID));
        this.fav_date = cursor.getLong(cursor.getColumnIndex(TWikiUserFav.FAV_DATE));
        this.fav_type = cursor.getInt(cursor.getColumnIndex(TWikiUserFav.FAV_TYPE));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TWikiUserFav.FAV_ID, fav_id);
        values.put(TWikiUserFav.FAV_OBJ_ID, fav_obj_id);
        values.put(TWikiUserFav.FAV_OBJ_TYPE, fav_obj_type);
        values.put(TWikiUserFav.FAV_UID, fav_uid);
        values.put(TWikiUserFav.FAV_DATE, fav_date);
        values.put(TWikiUserFav.FAV_TYPE, fav_type);
        values.put(TWikiUserFav.FK_WIKI, fkWikiId);
        return values;
    }
}
