package com.moefou.android.provider;

import com.moefou.android.provider.MoeTables.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoeDataBaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "moe.db";

    public static final int DATABASE_VERSION = 1;

    private static MoeDataBaseHelper mSingleton = null;

    private MoeDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized MoeDataBaseHelper getInstance(Context context) {
        mSingleton = new MoeDataBaseHelper(context);
        return mSingleton;
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase db = super.getWritableDatabase();
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TIcon.CREATE_TABLE_SQL);
        db.execSQL(TUser.CREATE_TABLE_SQL);

        db.execSQL(TWiki.CREATE_TABLE_SQL);
        db.execSQL(TWikiCover.CREATE_TABLE_SQL);
        db.execSQL(TWikiMeta.CREATE_TABLE_SQL);
        db.execSQL(TWikiUserFav.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TIcon.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TUser.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TWiki.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TWikiCover.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TWikiMeta.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TWikiUserFav.TABLE_NAME);
        onCreate(db);
    }
}
