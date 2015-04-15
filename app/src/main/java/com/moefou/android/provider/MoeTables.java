package com.moefou.android.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import java.util.HashMap;

public class MoeTables {

    public static final String AUTHORITY = "com.moefou.android";












    public static class TPlaylistJoinTFmCover {
        public static final String TABLE_NAME = TPlaylist.TABLE_NAME
                + " LEFT JOIN "
                + TFmCover.TABLE_NAME
                + " ON (T_FM._ID = T_FM_COVER.fk_fm)";

        public static final String FM_JOIN_FMCOVER = "FM_JOIN_FMCOVER";

        public static final String ID = "T_FM._ID";

        public static final String UP_ID = "T_FM.up_id";

        public static final String URL = "T_FM.url";

        public static final String STREAM_LENGTH = "T_FM.stream_length";

        public static final String STREAM_TIME = "T_FM.stream_time";

        public static final String FILE_SIZE = "T_FM.file_size";

        public static final String FILE_TYPE = "T_FM.file_type";

        public static final String WIKI_ID = "T_FM.wiki_id";

        public static final String WIKI_TYPE = "T_FM.wiki_type";

        public static final String TITLE = "T_FM.title";

        public static final String WIKI_TITLE = "T_FM.wiki_title";

        public static final String WIKI_URL = "T_FM.wiki_url";

        public static final String SUB_ID = "T_FM.sub_id";

        public static final String SUB_TYPE = "T_FM.sub_type";

        public static final String SUB_TITLE = "T_FM.sub_title";

        public static final String SUB_URL = "T_FM.sub_url";

        public static final String ARTIST = "T_FM.artist";

        public static final String FAV_WIKI = "T_FM.fav_wiki";

        public static final String FAV_SUB = "T_FM.fav_sub";

        public static final String FK_FM = "T_FM_COVER.fk_fm";

        public static final String SMALL = "T_FM_COVER.small";

        public static final String MEDIUM = "T_FM_COVER.medium";

        public static final String SQUARE = "T_FM_COVER.square";

        public static final String LARGE = "T_FM_COVER.large";

        public static final Uri CONTENT_URI_WIKI_JOIN_COVER = Uri.parse("content://"
                + AUTHORITY + "/" + FM_JOIN_FMCOVER);

        public static final String DEFAULT_SORT_ORDER = UP_ID + " desc";

        public static HashMap<String, String> projectionMap = new HashMap<String, String>();

        static {
            projectionMap.put(ID, ID);
            projectionMap.put(UP_ID, UP_ID);
            projectionMap.put(URL, URL);
            projectionMap.put(STREAM_LENGTH, STREAM_LENGTH);
            projectionMap.put(STREAM_TIME, STREAM_TIME);
            projectionMap.put(FILE_SIZE, FILE_SIZE);
            projectionMap.put(FILE_TYPE, FILE_TYPE);
            projectionMap.put(WIKI_ID, WIKI_ID);
            projectionMap.put(WIKI_TYPE, WIKI_TYPE);
            projectionMap.put(TITLE, TITLE);
            projectionMap.put(WIKI_TITLE, WIKI_TITLE);
            projectionMap.put(WIKI_URL, WIKI_URL);
            projectionMap.put(SUB_ID, SUB_ID);
            projectionMap.put(SUB_TYPE, SUB_TYPE);
            projectionMap.put(SUB_TITLE, SUB_TITLE);
            projectionMap.put(SUB_URL, SUB_URL);
            projectionMap.put(ARTIST, ARTIST);
            projectionMap.put(FAV_WIKI, FAV_WIKI);
            projectionMap.put(FAV_SUB, FAV_SUB);
            projectionMap.put(FK_FM, FK_FM);
            projectionMap.put(SMALL, SMALL);
            projectionMap.put(MEDIUM, MEDIUM);
            projectionMap.put(SQUARE, SQUARE);
            projectionMap.put(LARGE, LARGE);
        }
    }

    public static class TWikiJoinTWikiCover {
        public static final String TABLE_NAME = TWiki.TABLE_NAME
                + " LEFT JOIN "
                + TWikiCover.TABLE_NAME
                + " ON (T_WIKI._ID = T_WIKI_COVER.FK_WIKI)";

        public static final String WIKI_JOIN_WIKICOVER = "WIKI_JOIN_WIKICOVER";

        public static final String ID = "T_WIKI._ID";

        public static final String WIKI_ID = "T_WIKI.wiki_id";

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
            projectionMap.put(ID, ID);
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

    public static class TPlaylist {

        public static final String TABLE_NAME = "T_FM";

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/" + TABLE_NAME);

        public static final String ID = BaseColumns._ID;

        public static final String UP_ID = "up_id";

        public static final String URL = "url";

        public static final String STREAM_LENGTH = "stream_length";

        public static final String STREAM_TIME = "stream_time";

        public static final String FILE_SIZE = "file_size";

        public static final String FILE_TYPE = "file_type";

        public static final String WIKI_ID = "wiki_id";

        public static final String WIKI_TYPE = "wiki_type";

        public static final String TITLE = "title";

        public static final String WIKI_TITLE = "wiki_title";

        public static final String WIKI_URL = "wiki_url";

        public static final String SUB_ID = "sub_id";

        public static final String SUB_TYPE = "sub_type";

        public static final String SUB_TITLE = "sub_title";

        public static final String SUB_URL = "sub_url";

        public static final String ARTIST = "artist";

        public static final String FAV_WIKI = "fav_wiki";

        public static final String FAV_SUB = "fav_sub";

        //create table sql
        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UP_ID + " INTEGER,"
                + URL + " varchar(255),"
                + STREAM_LENGTH + " varchar(255),"
                + STREAM_TIME + " varchar(255),"
                + FILE_SIZE + " INTEGER,"
                + FILE_TYPE + " varchar(255),"
                + WIKI_ID + " INTEGER,"
                + WIKI_TYPE + " varchar(255),"
                + TITLE + " varchar(255),"
                + WIKI_TITLE + " varchar(255),"
                + WIKI_URL + " varchar(255),"
                + SUB_ID + " INTEGER,"
                + SUB_TYPE + " varchar(255),"
                + SUB_TITLE + " varchar(255),"
                + SUB_URL + " varchar(255),"
                + ARTIST + " varchar(255),"
                + FAV_WIKI + " varchar(255),"
                + FAV_SUB + " varchar(255))";

        //default sort order
        public static final String DEFAULT_SORT_ORDER = ID + " asc";

        //projection map
        public static HashMap<String, String> projectionMap = new HashMap<String, String>();

        static {
            projectionMap.put(ID, ID);
            projectionMap.put(UP_ID, UP_ID);
            projectionMap.put(URL, URL);
            projectionMap.put(STREAM_LENGTH, STREAM_LENGTH);
            projectionMap.put(STREAM_TIME, STREAM_TIME);
            projectionMap.put(FILE_SIZE, FILE_SIZE);
            projectionMap.put(FILE_TYPE, FILE_TYPE);
            projectionMap.put(WIKI_ID, WIKI_ID);
            projectionMap.put(WIKI_TYPE, WIKI_TYPE);
            projectionMap.put(TITLE, TITLE);
            projectionMap.put(WIKI_TITLE, WIKI_TITLE);
            projectionMap.put(WIKI_URL, WIKI_URL);
            projectionMap.put(SUB_ID, SUB_ID);
            projectionMap.put(SUB_TYPE, SUB_TYPE);
            projectionMap.put(SUB_TITLE, SUB_TITLE);
            projectionMap.put(SUB_URL, SUB_URL);
            projectionMap.put(ARTIST, ARTIST);
            projectionMap.put(FAV_WIKI, FAV_WIKI);
            projectionMap.put(FAV_SUB, FAV_SUB);
        }
    }

    public static class TFmCover {

        public static final String TABLE_NAME = "T_FM_COVER";

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + AUTHORITY + "/" + TABLE_NAME);

        public static final String ID = BaseColumns._ID;

        public static final String FK_FM = "fk_fm";

        public static final String SMALL = "small";

        public static final String MEDIUM = "medium";

        public static final String SQUARE = "square";

        public static final String LARGE = "large";

        //create table sql
        public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FK_FM + " INTEGER,"
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
            projectionMap.put(FK_FM, FK_FM);
            projectionMap.put(SMALL, SMALL);
            projectionMap.put(MEDIUM, MEDIUM);
            projectionMap.put(SQUARE, SQUARE);
            projectionMap.put(LARGE, LARGE);
        }
    }
}
