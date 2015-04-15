package com.moefou.android.object.fm;

import android.content.ContentValues;
import android.database.Cursor;

import com.moefou.android.provider.MoeTables.TPlaylist;

public class PlayList {

    private long up_id;

    private String url;

    private String stream_length;

    private String stream_time;

    private int file_size;

    private String file_type;

    private long wiki_id;

    private String wiki_type;

    private FmCover cover;

    private String title;

    private String wiki_title;

    private String wiki_url;

    private long sub_id;

    private String sub_type;

    private String sub_title;

    private String sub_url;

    private String artist;

    private String fav_wiki;

    private String fav_sub;

    public long getUp_id() {
        return up_id;
    }

    public void setUp_id(long up_id) {
        this.up_id = up_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStream_length() {
        return stream_length;
    }

    public void setStream_length(String stream_length) {
        this.stream_length = stream_length;
    }

    public String getStream_time() {
        return stream_time;
    }

    public void setStream_time(String stream_time) {
        this.stream_time = stream_time;
    }

    public int getFile_size() {
        return file_size;
    }

    public void setFile_size(int file_size) {
        this.file_size = file_size;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public long getWiki_id() {
        return wiki_id;
    }

    public void setWiki_id(long wiki_id) {
        this.wiki_id = wiki_id;
    }

    public String getWiki_type() {
        return wiki_type;
    }

    public void setWiki_type(String wiki_type) {
        this.wiki_type = wiki_type;
    }

    public FmCover getCover() {
        return cover;
    }

    public void setCover(FmCover cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWiki_title() {
        return wiki_title;
    }

    public void setWiki_title(String wiki_title) {
        this.wiki_title = wiki_title;
    }

    public String getWiki_url() {
        return wiki_url;
    }

    public void setWiki_url(String wiki_url) {
        this.wiki_url = wiki_url;
    }

    public long getSub_id() {
        return sub_id;
    }

    public void setSub_id(long sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getSub_url() {
        return sub_url;
    }

    public void setSub_url(String sub_url) {
        this.sub_url = sub_url;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getFav_wiki() {
        return fav_wiki;
    }

    public void setFav_wiki(String fav_wiki) {
        this.fav_wiki = fav_wiki;
    }

    public String getFav_sub() {
        return fav_sub;
    }

    public void setFav_sub(String fav_sub) {
        this.fav_sub = fav_sub;
    }


    public PlayList() {
    }

    public PlayList(Cursor cursor) {
        this.up_id = cursor.getLong(cursor.getColumnIndex(TPlaylist.UP_ID));
        this.url = cursor.getString(cursor.getColumnIndex(TPlaylist.URL));
        this.stream_length = cursor.getString(cursor.getColumnIndex(TPlaylist.STREAM_LENGTH));
        this.stream_time = cursor.getString(cursor.getColumnIndex(TPlaylist.STREAM_TIME));
        this.file_size = cursor.getInt(cursor.getColumnIndex(TPlaylist.FILE_SIZE));
        this.file_type = cursor.getString(cursor.getColumnIndex(TPlaylist.FILE_TYPE));
        this.wiki_id = cursor.getInt(cursor.getColumnIndex(TPlaylist.WIKI_ID));
        this.wiki_type = cursor.getString(cursor.getColumnIndex(TPlaylist.WIKI_TYPE));
        this.title = cursor.getString(cursor.getColumnIndex(TPlaylist.TITLE));
        this.wiki_title = cursor.getString(cursor.getColumnIndex(TPlaylist.WIKI_TITLE));
        this.wiki_url = cursor.getString(cursor.getColumnIndex(TPlaylist.WIKI_URL));
        this.sub_id = cursor.getLong(cursor.getColumnIndex(TPlaylist.SUB_ID));
        this.sub_type = cursor.getString(cursor.getColumnIndex(TPlaylist.SUB_TYPE));
        this.sub_title = cursor.getString(cursor.getColumnIndex(TPlaylist.SUB_TITLE));
        this.sub_url = cursor.getString(cursor.getColumnIndex(TPlaylist.SUB_URL));
        this.artist = cursor.getString(cursor.getColumnIndex(TPlaylist.ARTIST));
        this.fav_wiki = cursor.getString(cursor.getColumnIndex(TPlaylist.FAV_WIKI));
        this.fav_sub = cursor.getString(cursor.getColumnIndex(TPlaylist.FAV_SUB));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TPlaylist.UP_ID, up_id);
        values.put(TPlaylist.URL, url);
        values.put(TPlaylist.STREAM_LENGTH, stream_length);
        values.put(TPlaylist.STREAM_TIME, stream_time);
        values.put(TPlaylist.FILE_SIZE, file_size);
        values.put(TPlaylist.FILE_TYPE, file_type);
        values.put(TPlaylist.WIKI_ID, wiki_id);
        values.put(TPlaylist.WIKI_TYPE, wiki_type);
        values.put(TPlaylist.TITLE, title);
        values.put(TPlaylist.WIKI_TITLE, wiki_title);
        values.put(TPlaylist.WIKI_URL, wiki_url);
        values.put(TPlaylist.SUB_ID, sub_id);
        values.put(TPlaylist.SUB_TYPE, sub_type);
        values.put(TPlaylist.SUB_TITLE, sub_title);
        values.put(TPlaylist.SUB_URL, sub_url);
        values.put(TPlaylist.ARTIST, artist);
        values.put(TPlaylist.FAV_WIKI, fav_wiki);
        values.put(TPlaylist.FAV_SUB, fav_sub);
        return values;
    }

}
