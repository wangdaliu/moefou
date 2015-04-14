package com.moefou.android.object.fm;

import android.content.ContentValues;
import android.database.Cursor;

import com.moefou.android.provider.MoeTables.TFmCover;

public class FmCover {

    private long fkFmId;

    private String small;

    private String medium;

    private String square;

    private String large;

    public long getFkFmId() {
        return fkFmId;
    }

    public void setFkFmId(long fkFmId) {
        this.fkFmId = fkFmId;
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

    public FmCover() {
    }

    public FmCover(Cursor cursor) {
        this.fkFmId = cursor.getLong(cursor.getColumnIndex(TFmCover.FK_FM));
        this.small = cursor.getString(cursor.getColumnIndex(TFmCover.SMALL));
        this.medium = cursor.getString(cursor.getColumnIndex(TFmCover.MEDIUM));
        this.square = cursor.getString(cursor.getColumnIndex(TFmCover.SQUARE));
        this.large = cursor.getString(cursor.getColumnIndex(TFmCover.LARGE));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TFmCover.SMALL, small);
        values.put(TFmCover.MEDIUM, medium);
        values.put(TFmCover.SQUARE, square);
        values.put(TFmCover.LARGE, large);
        values.put(TFmCover.FK_FM, fkFmId);
        return values;
    }
}
