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

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Wiki")
public class Wiki extends Model {

    @Column(name = "_id")
    private int id;

    // 条目id号
    @Column(name = "wiki_id")
    private int wiki_id;

    // 标题
    @Column(name = "wiki_title")
    private String wiki_title;

    // 标题拼音
    @Column(name = "wiki_title_encode")
    private String wiki_title_encode;

    // 别名
    @Column(name = "wiki_name")
    private String wiki_name;

    // 条目类型
    @Column(name = "wiki_type")
    private String wiki_type;

    // 父级条目，当前系统中不启用
    @Column(name = "wiki_parent")
    private int wiki_parent;

    // 上映/发售等日期
    @Column(name = "wiki_date")
    private long wiki_date;

    // 最后修改时间
    @Column(name = "wiki_modified")
    private long wiki_modified;

    // 最后修改的用户
    @Column(name = "wiki_modified_user")
    private int wiki_modified_user;

    // 描述字段
    @Column(name = "wiki_meta")
    private List<WikiMeta> wiki_meta;

    // 电台中的地址
    @Column(name = "wiki_fm_url")
    private String wiki_fm_url;

    // 主站中的地址
    @Column(name = "wiki_url")
    private String wiki_url;

    // 条目封面，分有各种尺寸
    @Column(name = "wiki_cover")
    private WikiCover wiki_cover;

    @Column(name = "wiki_user_fav")
    private WikiUserFav wiki_user_fav;



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

    public List<WikiMeta> getWiki_meta() {
        return wiki_meta;
    }

    public void setWiki_meta(List<WikiMeta> wiki_meta) {
        this.wiki_meta = wiki_meta;
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
}