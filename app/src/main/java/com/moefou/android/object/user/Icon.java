package com.moefou.android.object.user;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Icon", id = BaseColumns._ID)
public class Icon extends Model {

    // 48x48
    @Column(name = "small")
    private String small;

    // 120x120，有些旧版头像无此尺寸，会默认使用large尺寸
    @Column(name = "medium")
    private String medium;

    // 204x204
    @Column(name = "large")
    private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
