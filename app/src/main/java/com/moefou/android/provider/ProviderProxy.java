package com.moefou.android.provider;

import android.database.sqlite.SQLiteOpenHelper;

public class ProviderProxy {

    public static SQLiteOpenHelper getSQLiteOpenHelper() {
        return MoeProvider.dbHelper;
    }
}
