package com.moefou.android.object.wiki;

import android.content.ContentValues;
import android.database.Cursor;

import com.moefou.android.provider.MoeTables.TWikiCover;

public class WikiCover {

    private long fkWikiId;

    // 96x96
    private String small;

    // 144x192
    private String medium;

    // 192x192
    private String square;

    // 原始尺寸
    private String large;

    public long getFkWikiId() {
        return fkWikiId;
    }

    public void setFkWikiId(long fkWikiId) {
        this.fkWikiId = fkWikiId;
    }

    public String getSmall() {
        return small;
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

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }


    public WikiCover() {
    }

    public WikiCover(Cursor cursor) {
        this.fkWikiId = cursor.getLong(cursor.getColumnIndex(TWikiCover.FK_WIKI));
        this.small = cursor.getString(cursor.getColumnIndex(TWikiCover.SMALL));
        this.medium = cursor.getString(cursor.getColumnIndex(TWikiCover.MEDIUM));
        this.square = cursor.getString(cursor.getColumnIndex(TWikiCover.SQUARE));
        this.large = cursor.getString(cursor.getColumnIndex(TWikiCover.LARGE));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TWikiCover.SMALL, small);
        values.put(TWikiCover.MEDIUM, medium);
        values.put(TWikiCover.SQUARE, square);
        values.put(TWikiCover.LARGE, large);
        values.put(TWikiCover.FK_WIKI, fkWikiId);
        return values;
    }
}
