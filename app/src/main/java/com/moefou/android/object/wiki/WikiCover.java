package com.moefou.android.object.wiki;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "WikiCover")
public class WikiCover extends Model {

    @Column(name = "_id")
    private int id;

    // 96x96
    @Column(name = "small")
    private String small;

    // 144x192
    @Column(name = "medium")
    private String medium;

    // 192x192
    @Column(name = "square")
    private String square;

    // 原始尺寸
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

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
