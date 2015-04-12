package com.moefou.android.object.user;

import android.content.ContentValues;
import android.database.Cursor;

import com.moefou.android.provider.MoeTables.TUser;

public class User {

    // 用户id号
    private int uid;

    // 用户名（登录名）
    private String user_name;

    // 昵称
    private String user_nickname;

    // 注册时间
    private long user_registered;

    // 上次活跃时间
    private long user_lastactivity;

    // 主站中的个人主页
    private String user_url;

    // 电台中的个人主页
    private String user_fm_url;

    // 头像，分为小中大三种尺寸
    private Icon user_avatar;

    // 参加的小组，按加入时间排列
    private String groups;

    // 粉丝们的uid
    private String follower;

    // 好友们的uid
    private String following;

    // 萌邮未读数
    private String msg;

    // 个人介绍
    private String about;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public long getUser_registered() {
        return user_registered;
    }

    public void setUser_registered(long user_registered) {
        this.user_registered = user_registered;
    }

    public long getUser_lastactivity() {
        return user_lastactivity;
    }

    public void setUser_lastactivity(long user_lastactivity) {
        this.user_lastactivity = user_lastactivity;
    }

    public String getUser_url() {
        return user_url;
    }

    public void setUser_url(String user_url) {
        this.user_url = user_url;
    }

    public String getUser_fm_url() {
        return user_fm_url;
    }

    public void setUser_fm_url(String user_fm_url) {
        this.user_fm_url = user_fm_url;
    }

    public Icon getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(Icon user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public User() {
    }

    public User(Cursor cursor) {
        this.uid = cursor.getInt(cursor.getColumnIndex(TUser.UID));
        this.user_name = cursor.getString(cursor.getColumnIndex(TUser.USER_NAME));
        this.user_nickname = cursor.getString(cursor.getColumnIndex(TUser.USER_NICKNAME));
        this.user_registered = cursor.getLong(cursor.getColumnIndex(TUser.USER_REGISTERED));
        this.user_lastactivity = cursor.getLong(cursor.getColumnIndex(TUser.USER_LAST_ACTIVITY));
        this.user_url = cursor.getString(cursor.getColumnIndex(TUser.USER_URL));
        this.user_url = cursor.getString(cursor.getColumnIndex(TUser.USER_URL));
        this.user_fm_url = cursor.getString(cursor.getColumnIndex(TUser.USER_FM_URL));
        this.groups = cursor.getString(cursor.getColumnIndex(TUser.GROUPS));
        this.follower = cursor.getString(cursor.getColumnIndex(TUser.FOLLOWER));
        this.following = cursor.getString(cursor.getColumnIndex(TUser.FOLLOWING));
        this.msg = cursor.getString(cursor.getColumnIndex(TUser.MSG));
        this.about = cursor.getString(cursor.getColumnIndex(TUser.ABOUT));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(TUser.UID, uid);
        values.put(TUser.USER_NAME, user_name);
        values.put(TUser.USER_NICKNAME, user_nickname);
        values.put(TUser.USER_REGISTERED, user_registered);
        values.put(TUser.USER_LAST_ACTIVITY, user_lastactivity);
        values.put(TUser.USER_URL, user_url);
        values.put(TUser.USER_FM_URL, user_fm_url);
        values.put(TUser.GROUPS, groups);
        values.put(TUser.FOLLOWER, follower);
        values.put(TUser.FOLLOWING, following);
        values.put(TUser.MSG, msg);
        values.put(TUser.ABOUT, about);
        return values;
    }
}
