//条目类型
//        类型名	描述	附注
//        tv	电视动画   	可使用anime统一替代tv、ova、oad、movie使用
//        ova	OVA动画	    可使用anime统一替代tv、ova、oad、movie使用
//        oad	OAD动画	    可使用anime统一替代tv、ova、oad、movie使用
//        movie	剧场版动画	可使用anime统一替代tv、ova、oad、movie使用
//        comic	漫画
//        music	音乐专辑
//        radio	音乐电台
package com.moefou.android.object.wiki;

import android.content.ContentValues;
import android.database.Cursor;

import com.moefou.android.provider.MoeTables.TWiki;

import java.util.List;

public class Wiki implements Comparable<Wiki> {

    private long id = -1;

    // 条目id号
    private int wiki_id;

    // 标题
    private String wiki_title;

    // 标题拼音
    private String wiki_title_encode;

    // 别名
    private String wiki_name;

    // 条目类型
    private String wiki_type;

    // 父级条目，当前系统中不启用
    private int wiki_parent;

    // 上映/发售等日期
    private long wiki_date;

    // 最后修改时间
    private long wiki_modified;

    // 最后修改的用户
    private int wiki_modified_user;

    // 电台中的地址
    private String wiki_fm_url;

    // 主站中的地址
    private String wiki_url;

    // 条目封面，分有各种尺寸
    private WikiCover wiki_cover;

    private WikiUserFav wiki_user_fav;

    // 描述字段
    private List<WikiMeta> wiki_meta;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getWiki_id() {
        return wiki_id;
    }

    public void setWiki_id(int wiki_id) {
        this.wiki_id = wiki_id;
    }

    public String getWiki_title() {
        return wiki_title;
    }

    public void setWiki_title(String wiki_title) {
        this.wiki_title = wiki_title;
    }

    public String getWiki_title_encode() {
        return wiki_title_encode;
    }

    public void setWiki_title_encode(String wiki_title_encode) {
        this.wiki_title_encode = wiki_title_encode;
    }

    public String getWiki_name() {
        return wiki_name;
    }

    public void setWiki_name(String wiki_name) {
        this.wiki_name = wiki_name;
    }

    public String getWiki_type() {
        return wiki_type;
    }

    public void setWiki_type(String wiki_type) {
        this.wiki_type = wiki_type;
    }

    public int getWiki_parent() {
        return wiki_parent;
    }

    public void setWiki_parent(int wiki_parent) {
        this.wiki_parent = wiki_parent;
    }

    public long getWiki_date() {
        return wiki_date;
    }

    public void setWiki_date(long wiki_date) {
        this.wiki_date = wiki_date;
    }

    public long getWiki_modified() {
        return wiki_modified;
    }

    public void setWiki_modified(long wiki_modified) {
        this.wiki_modified = wiki_modified;
    }

    public int getWiki_modified_user() {
        return wiki_modified_user;
    }

    public void setWiki_modified_user(int wiki_modified_user) {
        this.wiki_modified_user = wiki_modified_user;
    }

    public String getWiki_fm_url() {
        return wiki_fm_url;
    }

    public void setWiki_fm_url(String wiki_fm_url) {
        this.wiki_fm_url = wiki_fm_url;
    }

    public String getWiki_url() {
        return wiki_url;
    }

    public void setWiki_url(String wiki_url) {
        this.wiki_url = wiki_url;
    }

    public WikiCover getWiki_cover() {
        return wiki_cover;
    }

    public void setWiki_cover(WikiCover wiki_cover) {
        this.wiki_cover = wiki_cover;
    }

    public WikiUserFav getWiki_user_fav() {
        return wiki_user_fav;
    }

    public void setWiki_user_fav(WikiUserFav wiki_user_fav) {
        this.wiki_user_fav = wiki_user_fav;
    }

    public List<WikiMeta> getWiki_meta() {
        return wiki_meta;
    }

    public void setWiki_meta(List<WikiMeta> wiki_meta) {
        this.wiki_meta = wiki_meta;
    }

    @Override
    public int compareTo(Wiki another) {
        return (int) (another.wiki_date - wiki_date);
    }

    public Wiki() {
    }

    public Wiki(Cursor cursor) {
        this.id = cursor.getLong(cursor.getColumnIndex(TWiki.ID));
        this.wiki_id = cursor.getInt(cursor.getColumnIndex(TWiki.WIKI_ID));
        this.wiki_title = cursor.getString(cursor.getColumnIndex(TWiki.WIKI_TITLE));
        this.wiki_title_encode = cursor.getString(cursor.getColumnIndex(TWiki.WIKI_TITLE_ENCODE));
        this.wiki_name = cursor.getString(cursor.getColumnIndex(TWiki.WIKI_NAME));
        this.wiki_type = cursor.getString(cursor.getColumnIndex(TWiki.WIKI_TYPE));
        this.wiki_parent = cursor.getInt(cursor.getColumnIndex(TWiki.WIKI_PARENT));
        this.wiki_date = cursor.getLong(cursor.getColumnIndex(TWiki.WIKI_DATE));
        this.wiki_modified = cursor.getLong(cursor.getColumnIndex(TWiki.WIKI_MODIFIED));
        this.wiki_modified_user = cursor.getInt(cursor.getColumnIndex(TWiki.WIKI_MODIFIED_USER));
        this.wiki_fm_url = cursor.getString(cursor.getColumnIndex(TWiki.WIKI_FM_URL));
        this.wiki_url = cursor.getString(cursor.getColumnIndex(TWiki.WIKI_URL));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TWiki.WIKI_ID, wiki_id);
        values.put(TWiki.WIKI_TITLE, wiki_title);
        values.put(TWiki.WIKI_TITLE_ENCODE, wiki_title_encode);
        values.put(TWiki.WIKI_NAME, wiki_name);
        values.put(TWiki.WIKI_TYPE, wiki_type);
        values.put(TWiki.WIKI_PARENT, wiki_parent);
        values.put(TWiki.WIKI_DATE, wiki_date);
        values.put(TWiki.WIKI_MODIFIED, wiki_modified);
        values.put(TWiki.WIKI_MODIFIED_USER, wiki_modified_user);
        values.put(TWiki.WIKI_FM_URL, wiki_fm_url);
        values.put(TWiki.WIKI_URL, wiki_url);
        return values;
    }
}