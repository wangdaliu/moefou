//子条目类型
//        类型名	描述	所属条目类型
//        ep	动画、漫画章节	tv、ova、oad、movie、comic
//        song	音乐曲目	music

package com.moefou.android.object.sub;

import java.lang.reflect.Array;
import java.util.List;

public class Sub {
    // 子条目id号
    private int sub_id;

    // 所属条目的id
    private int sub_parent_wiki;

    // 父级子条目，在当前系统中不启用
    private int sub_parent;

    // 标题，在json化时中文已被编码
    private String sub_title;

    // 标题拼音
    private String sub_title_encode;

    // 子条目类型
    private String sub_type;

    // 子条目序号，如"4"、"15.5"、"special"等，一般用作排序
    private String sub_order;

    // 描述字段
    private List<SubMeta> sub_meta;

    // 子条目简介/备注等文字
    private String sub_about;

    // 额外数据（数组类型），一般为空
    private Array[] sub_data;

    // 上映or发售等日期
    private long sub_date;

    // 最后修改时间
    private long sub_modified;

    // 在主站中的地址
    private String sub_url;

    // 在电台中的地址
    private String sub_fm_url;

    // 显示出来的标题，一般为“类型.编号 标题”
    private String sub_view_title;

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public int getSub_parent_wiki() {
        return sub_parent_wiki;
    }

    public void setSub_parent_wiki(int sub_parent_wiki) {
        this.sub_parent_wiki = sub_parent_wiki;
    }

    public int getSub_parent() {
        return sub_parent;
    }

    public void setSub_parent(int sub_parent) {
        this.sub_parent = sub_parent;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getSub_title_encode() {
        return sub_title_encode;
    }

    public void setSub_title_encode(String sub_title_encode) {
        this.sub_title_encode = sub_title_encode;
    }

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getSub_order() {
        return sub_order;
    }

    public void setSub_order(String sub_order) {
        this.sub_order = sub_order;
    }

    public List<SubMeta> getSub_meta() {
        return sub_meta;
    }

    public void setSub_meta(List<SubMeta> sub_meta) {
        this.sub_meta = sub_meta;
    }

    public String getSub_about() {
        return sub_about;
    }

    public void setSub_about(String sub_about) {
        this.sub_about = sub_about;
    }

    public Array[] getSub_data() {
        return sub_data;
    }

    public void setSub_data(Array[] sub_data) {
        this.sub_data = sub_data;
    }

    public long getSub_date() {
        return sub_date;
    }

    public void setSub_date(long sub_date) {
        this.sub_date = sub_date;
    }

    public long getSub_modified() {
        return sub_modified;
    }

    public void setSub_modified(long sub_modified) {
        this.sub_modified = sub_modified;
    }

    public String getSub_url() {
        return sub_url;
    }

    public void setSub_url(String sub_url) {
        this.sub_url = sub_url;
    }

    public String getSub_fm_url() {
        return sub_fm_url;
    }

    public void setSub_fm_url(String sub_fm_url) {
        this.sub_fm_url = sub_fm_url;
    }

    public String getSub_view_title() {
        return sub_view_title;
    }

    public void setSub_view_title(String sub_view_title) {
        this.sub_view_title = sub_view_title;
    }
}
