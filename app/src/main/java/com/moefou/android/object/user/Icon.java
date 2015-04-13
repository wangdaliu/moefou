package com.moefou.android.object.user;

import android.content.ContentValues;
import android.database.Cursor;

import com.moefou.android.provider.MoeTables.TIcon;

public class Icon {

    private long fkUserId;

    // 48x48
    private String small;

    // 120x120，有些旧版头像无此尺寸，会默认使用large尺寸
    private String medium;

    // 204x204
    private String large;

    public String getSmall() {
        return small;
    }

    public long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }


    public Icon() {
    }

    public Icon(Cursor cursor) {
        this.fkUserId = cursor.getLong(cursor.getColumnIndex(TIcon.FK_USER));
        this.small = cursor.getString(cursor.getColumnIndex(TIcon.SMALL));
        this.medium = cursor.getString(cursor.getColumnIndex(TIcon.MEDIUM));
        this.large = cursor.getString(cursor.getColumnIndex(TIcon.LARGE));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TIcon.SMALL, small);
        values.put(TIcon.MEDIUM, medium);
        values.put(TIcon.LARGE, large);
        values.put(TIcon.FK_USER, fkUserId);
        return values;
    }
}
