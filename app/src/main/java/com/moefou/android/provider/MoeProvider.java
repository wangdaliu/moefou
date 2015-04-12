package com.moefou.android.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.moefou.android.provider.MoeTables.TIcon;
import com.moefou.android.provider.MoeTables.TUser;
import com.moefou.android.provider.MoeTables.TWiki;
import com.moefou.android.provider.MoeTables.TWikiCover;
import com.moefou.android.provider.MoeTables.TWikiMeta;
import com.moefou.android.provider.MoeTables.TWikiUserFav;
import com.moefou.android.provider.MoeTables.TWikiJoinTWikiCover;

public class MoeProvider extends ContentProvider {

    static MoeDataBaseHelper dbHelper;

    //content uri match id
    private static final int ICON = 1;

    private static final int ICON_ID = 2;

    private static final int USER = 3;

    private static final int USER_ID = 4;

    private static final int WIKI = 5;

    private static final int WIKI_ID = 6;

    private static final int WIKI_COVER = 7;

    private static final int WIKI_COVER_ID = 8;

    private static final int WIKI_META = 9;

    private static final int WIKI_META_ID = 10;

    private static final int WIKI_USERFAV = 11;

    private static final int WIKI_USERFAV_ID = 12;

    private static final int WIKI_WITH_WIKICOVER = 13;

    //define the uri matcher
    private static UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(MoeTables.AUTHORITY, TIcon.TABLE_NAME, ICON);
        matcher.addURI(MoeTables.AUTHORITY, TIcon.TABLE_NAME + "/#", ICON_ID);

        matcher.addURI(MoeTables.AUTHORITY, TUser.TABLE_NAME, USER);
        matcher.addURI(MoeTables.AUTHORITY, TUser.TABLE_NAME + "/#", USER_ID);

        matcher.addURI(MoeTables.AUTHORITY, TWiki.TABLE_NAME, WIKI);
        matcher.addURI(MoeTables.AUTHORITY, TWiki.TABLE_NAME + "/#", WIKI_ID);

        matcher.addURI(MoeTables.AUTHORITY, TWikiCover.TABLE_NAME, WIKI_COVER);
        matcher.addURI(MoeTables.AUTHORITY, TWikiCover.TABLE_NAME + "/#", WIKI_COVER_ID);

        matcher.addURI(MoeTables.AUTHORITY, TWikiMeta.TABLE_NAME, WIKI_META);
        matcher.addURI(MoeTables.AUTHORITY, TWikiMeta.TABLE_NAME + "/#", WIKI_META_ID);

        matcher.addURI(MoeTables.AUTHORITY, TWikiUserFav.TABLE_NAME, WIKI_USERFAV);
        matcher.addURI(MoeTables.AUTHORITY, TWikiUserFav.TABLE_NAME + "/#", WIKI_USERFAV_ID);

        matcher.addURI(MoeTables.AUTHORITY, TWikiJoinTWikiCover.WIKI_JOIN_WIKICOVER, WIKI_WITH_WIKICOVER);
    }

    public static SQLiteOpenHelper getSQLiteOpenHelper() {
        return dbHelper;
    }

    @Override
    public boolean onCreate() {
        dbHelper = MoeDataBaseHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        switch (matcher.match(uri)) {
            case ICON:
                qb.setTables(TIcon.TABLE_NAME);
                qb.setProjectionMap(TIcon.projectionMap);
                break;
            case ICON_ID:
                qb.setTables(TIcon.TABLE_NAME);
                qb.setProjectionMap(TIcon.projectionMap);
                qb.appendWhere(TIcon.ID + "=" + uri.getPathSegments().get(1));
                break;
            case USER:
                qb.setTables(TUser.TABLE_NAME);
                qb.setProjectionMap(TUser.projectionMap);
                break;
            case USER_ID:
                qb.setTables(TUser.TABLE_NAME);
                qb.setProjectionMap(TUser.projectionMap);
                qb.appendWhere(TUser.ID + "=" + uri.getPathSegments().get(1));
                break;
            case WIKI:
                qb.setTables(TWiki.TABLE_NAME);
                qb.setProjectionMap(TWiki.projectionMap);
                break;
            case WIKI_ID:
                qb.setTables(TWiki.TABLE_NAME);
                qb.setProjectionMap(TWiki.projectionMap);
                qb.appendWhere(TWiki.ID + "=" + uri.getPathSegments().get(1));
                break;
            case WIKI_COVER:
                qb.setTables(TWikiCover.TABLE_NAME);
                qb.setProjectionMap(TWikiCover.projectionMap);
                break;
            case WIKI_COVER_ID:
                qb.setTables(TWikiCover.TABLE_NAME);
                qb.setProjectionMap(TWikiCover.projectionMap);
                qb.appendWhere(TWikiCover.ID + "=" + uri.getPathSegments().get(1));
                break;
            case WIKI_META:
                qb.setTables(TWikiMeta.TABLE_NAME);
                qb.setProjectionMap(TWikiMeta.projectionMap);
                break;
            case WIKI_META_ID:
                qb.setTables(TWikiMeta.TABLE_NAME);
                qb.setProjectionMap(TWikiMeta.projectionMap);
                qb.appendWhere(TWikiMeta.ID + "=" + uri.getPathSegments().get(1));
                break;
            case WIKI_USERFAV:
                qb.setTables(TWikiUserFav.TABLE_NAME);
                qb.setProjectionMap(TWikiUserFav.projectionMap);
                break;
            case WIKI_USERFAV_ID:
                qb.setTables(TWikiUserFav.TABLE_NAME);
                qb.setProjectionMap(TWikiUserFav.projectionMap);
                qb.appendWhere(TWikiUserFav.ID + "=" + uri.getPathSegments().get(1));
                break;
            case WIKI_WITH_WIKICOVER:
                qb.setTables(TWikiJoinTWikiCover.TABLE_NAME);
                qb.setProjectionMap(TWikiJoinTWikiCover.projectionMap);
                break;
            default:
                throw new IllegalArgumentException("Unknow uri: " + uri);
        }

        // set sort order
        if (TextUtils.isEmpty(sortOrder)) {
            sortOrder = getSortOrder(uri);
        }

        // start query
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = qb.query(db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        // watch the uri for changes
        //  getContext().getContentResolver().notifyChange(uri, null);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = null;
        switch (matcher.match(uri)) {
            case ICON:
                tableName = TIcon.TABLE_NAME;
                break;
            case USER:
                tableName = TUser.TABLE_NAME;
                break;
            case WIKI:
                tableName = TWiki.TABLE_NAME;
                break;
            case WIKI_COVER:
                tableName = TWikiCover.TABLE_NAME;
                break;
            case WIKI_META:
                tableName = TWikiMeta.TABLE_NAME;
                break;
            case WIKI_USERFAV:
                tableName = TWikiUserFav.TABLE_NAME;
                break;
            case WIKI_WITH_WIKICOVER:
                tableName = TWikiJoinTWikiCover.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknow uri: " + uri);
        }

        // make a copy of the values
        ContentValues v;
        if (values != null) {
            v = new ContentValues(values);
        } else {
            v = new ContentValues();
        }

        // store the data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insert(tableName, "NULL", v);
        if (rowId > 0) {
            Uri catUri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(uri, null);
            return catUri;
        }
        throw new RuntimeException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        String rowId = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (matcher.match(uri)) {
            case ICON:
                count = db.delete(TIcon.TABLE_NAME, selection, selectionArgs);
                break;
            case ICON_ID:
                rowId = uri.getPathSegments().get(1);
                count = db.delete(TIcon.TABLE_NAME, whereWithId(rowId, selection), null);
                break;
            case USER:
                count = db.delete(TUser.TABLE_NAME, selection, selectionArgs);
                break;
            case USER_ID:
                rowId = uri.getPathSegments().get(1);
                count = db.delete(TUser.TABLE_NAME, whereWithId(rowId, selection), null);
                break;
            case WIKI:
                count = db.delete(TWiki.TABLE_NAME, selection, selectionArgs);
                break;
            case WIKI_ID:
                rowId = uri.getPathSegments().get(1);
                count = db.delete(TWiki.TABLE_NAME, whereWithId(rowId, selection), null);
                break;
            case WIKI_COVER:
                count = db.delete(TWikiCover.TABLE_NAME, selection, selectionArgs);
                break;
            case WIKI_COVER_ID:
                rowId = uri.getPathSegments().get(1);
                count = db.delete(TWikiCover.TABLE_NAME, whereWithId(rowId, selection), null);
                break;
            case WIKI_META:
                count = db.delete(TWikiMeta.TABLE_NAME, selection, selectionArgs);
                break;
            case WIKI_META_ID:
                rowId = uri.getPathSegments().get(1);
                count = db.delete(TWikiMeta.TABLE_NAME, whereWithId(rowId, selection), null);
                break;
            case WIKI_USERFAV:
                count = db.delete(TWikiUserFav.TABLE_NAME, selection, selectionArgs);
                break;
            case WIKI_USERFAV_ID:
                rowId = uri.getPathSegments().get(1);
                count = db.delete(TWikiUserFav.TABLE_NAME, whereWithId(rowId, selection), null);
                break;
            default:
                throw new IllegalArgumentException("Unknow uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        long id = 0;
        // store the data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (matcher.match(uri)) {
            case ICON:
                count = db.update(TIcon.TABLE_NAME, values, selection, selectionArgs);
                break;
            case ICON_ID:
                id = Long.parseLong(uri.getPathSegments().get(1));
                count = db.update(TIcon.TABLE_NAME, values, TIcon.ID + "=" + id, null);
                break;
            case USER:
                count = db.update(TUser.TABLE_NAME, values, selection, selectionArgs);
                break;
            case USER_ID:
                id = Long.parseLong(uri.getPathSegments().get(1));
                count = db.update(TUser.TABLE_NAME, values, TUser.ID + "=" + id, null);
                break;
            case WIKI:
                count = db.update(TWiki.TABLE_NAME, values, selection, selectionArgs);
                break;
            case WIKI_ID:
                id = Long.parseLong(uri.getPathSegments().get(1));
                count = db.update(TWiki.TABLE_NAME, values, TWiki.ID + "=" + id, null);
                break;
            case WIKI_COVER:
                count = db.update(TWikiCover.TABLE_NAME, values, selection, selectionArgs);
                break;
            case WIKI_COVER_ID:
                id = Long.parseLong(uri.getPathSegments().get(1));
                count = db.update(TWikiCover.TABLE_NAME, values, TWikiCover.ID + "=" + id, null);
                break;
            case WIKI_META:
                count = db.update(TWikiMeta.TABLE_NAME, values, selection, selectionArgs);
                break;
            case WIKI_META_ID:
                id = Long.parseLong(uri.getPathSegments().get(1));
                count = db.update(TWikiMeta.TABLE_NAME, values, TWikiMeta.ID + "=" + id, null);
                break;
            case WIKI_USERFAV:
                count = db.update(TWikiUserFav.TABLE_NAME, values, selection, selectionArgs);
                break;
            case WIKI_USERFAV_ID:
                id = Long.parseLong(uri.getPathSegments().get(1));
                count = db.update(TWikiUserFav.TABLE_NAME, values, TWikiUserFav.ID + "=" + id, null);
                break;
            default:
                throw new IllegalArgumentException("Unknow uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    private String whereWithId(String id, String selection) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID=");
        sb.append(id);
        if (selection != null) {
            sb.append(" AND (");
            sb.append(selection);
            sb.append(')');
        }
        return sb.toString();
    }

    private String getSortOrder(Uri uri) {
        String newOrder;
        switch (matcher.match(uri)) {
            case ICON:
            case ICON_ID:
                newOrder = TIcon.DEFAULT_SORT_ORDER;
                break;
            case USER:
            case USER_ID:
                newOrder = TUser.DEFAULT_SORT_ORDER;
                break;
            case WIKI:
            case WIKI_ID:
                newOrder = TWiki.DEFAULT_SORT_ORDER;
                break;
            case WIKI_COVER:
            case WIKI_COVER_ID:
                newOrder = TWikiCover.DEFAULT_SORT_ORDER;
                break;
            case WIKI_META:
            case WIKI_META_ID:
                newOrder = TWikiMeta.DEFAULT_SORT_ORDER;
                break;
            case WIKI_USERFAV:
            case WIKI_USERFAV_ID:
                newOrder = TWikiUserFav.DEFAULT_SORT_ORDER;
                break;
            case WIKI_WITH_WIKICOVER:
                newOrder = TWikiJoinTWikiCover.DEFAULT_SORT_ORDER;
                break;
            default:
                throw new IllegalArgumentException("Unknow uri: " + uri);
        }
        return newOrder;
    }
}
