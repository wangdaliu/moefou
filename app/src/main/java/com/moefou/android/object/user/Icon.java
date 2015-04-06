package com.moefou.android.object.user;

public class Icon {

    // 48x48
    private String small;
    // 120x120，有些旧版头像无此尺寸，会默认使用large尺寸
    private String medium;
    // 204x204
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
