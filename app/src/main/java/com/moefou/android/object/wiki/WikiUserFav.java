package com.moefou.android.object.wiki;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "WikiUserFav")
public class WikiUserFav extends Model {

    @Column(name = "_id")
    private int id;

    @Column(name = "fav_id")
    private int fav_id;

    @Column(name = "fav_obj_id")
    private int fav_obj_id;

    @Column(name = "fav_obj_type")
    private String fav_obj_type;

    @Column(name = "fav_uid")
    private int fav_uid;

    @Column(name = "fav_date")
    private long fav_date;

    @Column(name = "fav_type")
    private int fav_type;

    public int getFav_id() {
        return fav_id;
    }

    public void setFav_id(int fav_id) {
        this.fav_id = fav_id;
    }

    public int getFav_obj_id() {
        return fav_obj_id;
    }

    public void setFav_obj_id(int fav_obj_id) {
        this.fav_obj_id = fav_obj_id;
    }

    public String getFav_obj_type() {
        return fav_obj_type;
    }

    public void setFav_obj_type(String fav_obj_type) {
        this.fav_obj_type = fav_obj_type;
    }

    public int getFav_uid() {
        return fav_uid;
    }

    public void setFav_uid(int fav_uid) {
        this.fav_uid = fav_uid;
    }

    public long getFav_date() {
        return fav_date;
    }

    public void setFav_date(long fav_date) {
        this.fav_date = fav_date;
    }

    public int getFav_type() {
        return fav_type;
    }

    public void setFav_type(int fav_type) {
        this.fav_type = fav_type;
    }
}
