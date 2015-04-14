package com.moefou.android.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.moefou.android.provider.MoeTables.TIcon;
import com.moefou.android.provider.MoeTables.TUser;
import com.moefou.android.provider.MoeTables.TWiki;
import com.moefou.android.provider.MoeTables.TWikiCover;
import com.moefou.android.provider.MoeTables.TWikiMeta;
import com.moefou.android.provider.MoeTables.TWikiUserFav;
import com.moefou.android.provider.MoeTables.TPlaylist;
import com.moefou.android.provider.MoeTables.TFmCover;

public class MoeDataBaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "moe.db";

    public static final int DATABASE_VERSION = 1;

    private static MoeDataBaseHelper mSingleton = null;

    private static final String delete_wiki_cover_trigger = "CREATE TRIGGER delete_wiki_cover_trigger AFTER DELETE ON "
            + TWiki.TABLE_NAME
            + " for each row BEGIN "
            + " DELETE FROM "
            + TWikiCover.TABLE_NAME
            + " WHERE "
            + TWikiCover.FK_WIKI + "=OLD." + TWiki.ID + "; END;";

    private static final String delete_wiki_meta_trigger = "CREATE TRIGGER delete_wiki_meta_trigger AFTER DELETE ON "
            + TWiki.TABLE_NAME
            + " for each row BEGIN "
            + " DELETE FROM "
            + TWikiMeta.TABLE_NAME
            + " WHERE "
            + TWikiMeta.FK_WIKI + "=OLD." + TWiki.ID + "; END;";

    private static final String delete_wiki_user_fav_trigger = "CREATE TRIGGER delete_wiki_user_fav_trigger AFTER DELETE ON "
            + TWiki.TABLE_NAME
            + " for each row BEGIN "
            + " DELETE FROM "
            + TWikiUserFav.TABLE_NAME
            + " WHERE "
            + TWikiUserFav.FK_WIKI + "=OLD." + TWiki.ID + "; END;";

    private static final String delete_icon_trigger = "CREATE TRIGGER delete_icon_trigger AFTER DELETE ON "
            + TUser.TABLE_NAME
            + " for each row BEGIN "
            + " DELETE FROM "
            + TIcon.TABLE_NAME
            + " WHERE "
            + TIcon.FK_USER + "=OLD." + TUser.ID + "; END;";

    private static final String delete_fm_cover_trigger = "CREATE TRIGGER delete_fm_cover_trigger AFTER DELETE ON "
            + TPlaylist.TABLE_NAME
            + " for each row BEGIN "
            + " DELETE FROM "
            + TFmCover.TABLE_NAME
            + " WHERE "
            + TFmCover.FK_FM + "=OLD." + TPlaylist.ID + "; END;";

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

        db.execSQL(TPlaylist.CREATE_TABLE_SQL);
        db.execSQL(TFmCover.CREATE_TABLE_SQL);

        db.execSQL(delete_wiki_cover_trigger);
        db.execSQL(delete_wiki_meta_trigger);
        db.execSQL(delete_wiki_user_fav_trigger);
        db.execSQL(delete_icon_trigger);
        db.execSQL(delete_fm_cover_trigger);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TIcon.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TUser.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TWiki.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TWikiCover.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TWikiMeta.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TWikiUserFav.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TPlaylist.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TFmCover.TABLE_NAME);
        onCreate(db);
    }
}
