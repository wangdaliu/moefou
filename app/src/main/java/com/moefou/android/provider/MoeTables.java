package com.moefou.android.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import java.util.HashMap;

public class MoeTables {

    public static final String AUTHORITY = "com.moefou.android";

    public static class TWikiJoinTWikiCover {
        public static final String TABLE_NAME = TWiki.TABLE_NAME
                + " LEFT JOIN "
                + TWikiCover.TABLE_NAME
                + " ON (T_WIKI._ID = T_WIKI_COVER.FK_WIKI)";

        public static final String WIKI_JOIN_WIKICOVER = "WIKI_JOIN_WIKICOVER";

        public static final String WIKI_ID = "T_WIKI._ID";

        public static final String WIKI_TITLE = "T_WIKI.wiki_title";

        public static final String WIKI_TYPE = "T_WIKI.wiki_type";

        public static final String WIKI_DATE = "T_WIKI.wiki_date";

        public static final String WIKI_MODIFIED = "T_WIKI.wiki_modified";

        public static final String WIKI_COVER_SMALL = "T_WIKI_COVER.small";

        public static final Uri CONTENT_URI_WIKI_JOIN_COVER = Uri.parse("content://"
                + AUTHORITY + "/" + WIKI_JOIN_WIKICOVER);

        public static final String DEFAULT_SORT_ORDER = WIKI_DATE + " desc";

        public static HashMap<String, String> projectionMap = new HashMap<String, String>();

        static {
            projectionMap.put(WIKI_ID, WIKI_ID);
            projectionMap.put(WIKI_TITLE, WIKI_TITLE);
            projectionMap.put(WIKI_TYPE, WIKI_TYPE);
            projectionMap.put(WIKI_DATE, WIKI_DATE);
            projectionMap.put(WIKI_MODIFIED, WIKI_MODIFIED);
            projectionMap.put(WIKI_COVER_SMALL, WIKI_COVER_SMALL);
        }
    }


    public static class TUser {
        public static final String TABLE_NAME = "T_USER";

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/" + TABLE_NAME);

        public static final String ID = BaseColumns._ID;

        public static final String UID = "uid";

        public static final String USER_NAME = "user_name";

        public static final String USER_NICKNAME = "user_nickname";

        public static final String USER_REGISTERED = "user_registered";

        public static final String USER_LAST_ACTIVITY = "user_lastactivity";

        public static final String USER_URL = "user_url";

        public static final String USER_FM_URL = "user_fm_url";

        public static final String GROUPS = "groups";

        public static final String FOLLOWER = "follower";

        public static final String FOLLOWING = "following";

        public static final String MSG = "msg";

        public static final String ABOUT = "about";

        //create table sql
        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UID + " INTEGER,"
                + USER_NAME + " varchar(255),"
                + USER_NICKNAME + " varchar(255),"
                + USER_REGISTERED + " INTEGER,"
                + USER_LAST_ACTIVITY + " INTEGER,"
                + USER_URL + " varchar(255),"
                + USER_FM_URL + " varchar(255),"
                + GROUPS + " varchar(255),"
                + FOLLOWER + " varchar(255),"
                + FOLLOWING + " varchar(255),"
                + MSG + " varchar(255),"
                + ABOUT + " varchar(255))";

        //default sort order
        public static final String DEFAULT_SORT_ORDER = ID + " asc";

        //projection map
        public static HashMap<String, String> projectionMap = new HashMap<String, String>();

        static {
            projectionMap.put(ID, ID);
            projectionMap.put(UID, UID);
            projectionMap.put(USER_NAME, USER_NAME);
            projectionMap.put(USER_NICKNAME, USER_NICKNAME);
            projectionMap.put(USER_REGISTERED, USER_REGISTERED);
            projectionMap.put(USER_LAST_ACTIVITY, USER_LAST_ACTIVITY);
            projectionMap.put(USER_URL, USER_URL);
            projectionMap.put(USER_FM_URL, USER_FM_URL);
            projectionMap.put(GROUPS, GROUPS);
            projectionMap.put(FOLLOWER, FOLLOWER);
            projectionMap.put(FOLLOWING, FOLLOWING);
            projectionMap.put(MSG, MSG);
            projectionMap.put(ABOUT, ABOUT);
        }
    }

    public static class TIcon {

        public static final String TABLE_NAME = "T_ICON";

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/" + TABLE_NAME);

        public static final String ID = BaseColumns._ID;

        public static final String FK_USER = "fk_user";

        public static final String SMALL = "small";

        public static final String MEDIUM = "medium";

        public static final String LARGE = "large";

        //create table sql
        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FK_USER + " INTEGER,"
                + SMALL + " varchar(255),"
                + MEDIUM + " varchar(255),"
                + LARGE + " varchar(255))";

        //default sort order
        public static final String DEFAULT_SORT_ORDER = ID + " asc";

        //projection map
        public static HashMap<String, String> projectionMap = new HashMap<String, String>();

        static {
            projectionMap.put(ID, ID);
            projectionMap.put(FK_USER, FK_USER);
            projectionMap.put(SMALL, SMALL);
            projectionMap.put(MEDIUM, MEDIUM);
            projectionMap.put(LARGE, LARGE);
        }
    }

    public static class TWiki {

        public static final String TABLE_NAME = "T_WIKI";

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/" + TABLE_NAME);

        public static final String ID = BaseColumns._ID;

        public static final String WIKI_ID = "wiki_id";

        public static final String WIKI_TITLE = "wiki_title";

        public static final String WIKI_TITLE_ENCODE = "wiki_title_encode";

        public static final String WIKI_NAME = "wiki_name";

        public static final String WIKI_TYPE = "wiki_type";

        public static final String WIKI_PARENT = "wiki_parent";

        public static final String WIKI_DATE = "wiki_date";

        public static final String WIKI_MODIFIED = "wiki_modified";

        public static final String WIKI_MODIFIED_USER = "wiki_modified_user";

        public static final String WIKI_FM_URL = "wiki_fm_url";

        public static final String WIKI_URL = "wiki_url";

        //create table sql
        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WIKI_ID + " INTEGER,"
                + WIKI_TITLE + " varchar(255),"
                + WIKI_TITLE_ENCODE + " varchar(255),"
                + WIKI_NAME + " varchar(255),"
                + WIKI_TYPE + " varchar(255),"
                + WIKI_PARENT + " INTEGER,"
                + WIKI_DATE + " INTEGER,"
                + WIKI_MODIFIED + " INTEGER,"
                + WIKI_MODIFIED_USER + " INTEGER,"
                + WIKI_FM_URL + " varchar(255),"
                + WIKI_URL + " varchar(255))";

        //default sort order
        public static final String DEFAULT_SORT_ORDER = ID + " asc";

        //projection map
        public static HashMap<String, String> projectionMap = new HashMap<String, String>();

        static {
            projectionMap.put(ID, ID);
            projectionMap.put(WIKI_ID, WIKI_ID);
            projectionMap.put(WIKI_TITLE, WIKI_TITLE);
            projectionMap.put(WIKI_TITLE_ENCODE, WIKI_TITLE_ENCODE);
            projectionMap.put(WIKI_NAME, WIKI_NAME);
            projectionMap.put(WIKI_TYPE, WIKI_TYPE);
            projectionMap.put(WIKI_PARENT, WIKI_PARENT);
            projectionMap.put(WIKI_DATE, WIKI_DATE);
            projectionMap.put(WIKI_MODIFIED, WIKI_MODIFIED);
            projectionMap.put(WIKI_MODIFIED_USER, WIKI_MODIFIED_USER);
            projectionMap.put(WIKI_FM_URL, WIKI_FM_URL);
            projectionMap.put(WIKI_URL, WIKI_URL);
        }
    }


    public static class TWikiCover {

        public static final String TABLE_NAME = "T_WIKI_COVER";

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/" + TABLE_NAME);

        public static final String ID = BaseColumns._ID;

        public static final String FK_WIKI = "fk_wiki";

        public static final String SMALL = "small";

        public static final String MEDIUM = "medium";

        public static final String SQUARE = "square";

        public static final String LARGE = "large";

        //create table sql
        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FK_WIKI + " INTEGER,"
                + SMALL + " varchar(255),"
                + MEDIUM + " varchar(255),"
                + SQUARE + " varchar(255),"
                + LARGE + " varchar(255))";

        //default sort order
        public static final String DEFAULT_SORT_ORDER = ID + " asc";

        //projection map
        public static HashMap<String, String> projectionMap = new HashMap<String, String>();

        static {
            projectionMap.put(ID, ID);
            projectionMap.put(FK_WIKI, FK_WIKI);
            projectionMap.put(SMALL, SMALL);
            projectionMap.put(MEDIUM, MEDIUM);
            projectionMap.put(SQUARE, SQUARE);
            projectionMap.put(LARGE, LARGE);
        }
    }

    public static class TWikiUserFav {

        public static final String TABLE_NAME = "T_WIKI_USER_FAV";

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/" + TABLE_NAME);

        public static final String ID = BaseColumns._ID;

        public static final String FK_WIKI = "fk_wiki";

        public static final String FAV_ID = "fav_id";

        public static final String FAV_OBJ_ID = "fav_obj_id";

        public static final String FAV_OBJ_TYPE = "fav_obj_type";

        public static final String FAV_UID = "fav_uid";

        public static final String FAV_DATE = "fav_date";

        public static final String FAV_TYPE = "fav_type";

        //create table sql
        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FK_WIKI + " INTEGER,"
                + FAV_ID + " INTEGER,"
                + FAV_OBJ_ID + " INTEGER,"
                + FAV_OBJ_TYPE + " varchar(255),"
                + FAV_UID + " INTEGER,"
                + FAV_DATE + " INTEGER,"
                + FAV_TYPE + " INTEGER)";

        //default sort order
        public static final String DEFAULT_SORT_ORDER = ID + " asc";

        //projection map
        public static HashMap<String, String> projectionMap = new HashMap<String, String>();

        static {
            projectionMap.put(ID, ID);
            projectionMap.put(FK_WIKI, FK_WIKI);
            projectionMap.put(FAV_ID, FAV_ID);
            projectionMap.put(FAV_OBJ_ID, FAV_OBJ_ID);
            projectionMap.put(FAV_OBJ_TYPE, FAV_OBJ_TYPE);
            projectionMap.put(FAV_UID, FAV_UID);
            projectionMap.put(FAV_DATE, FAV_DATE);
            projectionMap.put(FAV_TYPE, FAV_TYPE);
        }
    }


    public static class TWikiMeta {

        public static final String TABLE_NAME = "T_WIKI_META";

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/" + TABLE_NAME);

        public static final String ID = BaseColumns._ID;

        public static final String FK_WIKI = "fk_wiki";

        public static final String META_KEY = "meta_key";

        public static final String META_VALUE = "meta_value";

        public static final String META_TYPE = "meta_type";

        //create table sql
        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FK_WIKI + " INTEGER,"
                + META_KEY + " varchar(255),"
                + META_VALUE + " varchar(255),"
                + META_TYPE + " INTEGER)";

        //default sort order
        public static final String DEFAULT_SORT_ORDER = ID + " asc";

        //projection map
        public static HashMap<String, String> projectionMap = new HashMap<String, String>();

        static {
            projectionMap.put(ID, ID);
            projectionMap.put(FK_WIKI, FK_WIKI);
            projectionMap.put(META_KEY, META_KEY);
            projectionMap.put(META_VALUE, META_VALUE);
            projectionMap.put(META_TYPE, META_TYPE);
        }
    }
}
