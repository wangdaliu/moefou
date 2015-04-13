package com.moefou.android.object.wiki;

import android.content.ContentValues;
import android.database.Cursor;

import com.moefou.android.provider.MoeTables.TWikiMeta;

public class WikiMeta {

    private long fkWikiId;

    // 字段名
    private String meta_key;

    // 字段值
//    private String meta_value;

    // 字段类型，1为小字段，2为大字段
    private int meta_type;

    public long getFkWikiId() {
        return fkWikiId;
    }

    public void setFkWikiId(long fkWikiId) {
        this.fkWikiId = fkWikiId;
    }

    public String getMeta_key() {
        return meta_key;
    }

    public void setMeta_key(String meta_key) {
        this.meta_key = meta_key;
    }

//    public String getMeta_value() {
//        return meta_value;
//    }
//
//    public void setMeta_value(String meta_value) {
//        this.meta_value = meta_value;
//    }

    public int getMeta_type() {
        return meta_type;
    }

    public void setMeta_type(int meta_type) {
        this.meta_type = meta_type;
    }


    public WikiMeta() {
    }

    public WikiMeta(Cursor cursor) {
        this.fkWikiId = cursor.getLong(cursor.getColumnIndex(TWikiMeta.FK_WIKI));
        this.meta_key = cursor.getString(cursor.getColumnIndex(TWikiMeta.META_KEY));
//        this.meta_value = cursor.getString(cursor.getColumnIndex(TWikiMeta.META_VALUE));
        this.meta_type = cursor.getInt(cursor.getColumnIndex(TWikiMeta.META_TYPE));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TWikiMeta.META_KEY, meta_key);
//        values.put(TWikiMeta.META_VALUE, meta_value);
        values.put(TWikiMeta.META_TYPE, meta_type);
        values.put(TWikiMeta.FK_WIKI, fkWikiId);
        return values;
    }
}
