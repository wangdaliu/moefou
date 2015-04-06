package com.moefou.android.object.user;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "User")
public class User extends Model {

    @Column(name = "_id")
    private int id;

    // 用户id号
    @Column(name = "uid")
    private int uid;

    // 用户名（登录名）
    @Column(name = "user_name")
    private String user_name;

    // 昵称
    @Column(name = "user_nickname")
    private String user_nickname;

    // 注册时间
    @Column(name = "user_registered")
    private long user_registered;

    // 上次活跃时间
    @Column(name = "user_lastactivity")
    private long user_lastactivity;

    // 主站中的个人主页
    @Column(name = "user_url")
    private String user_url;

    // 电台中的个人主页
    @Column(name = "user_fm_url")
    private String user_fm_url;

    // 头像，分为小中大三种尺寸
    @Column(name = "user_avatar")
    private Icon user_avatar;

    // 参加的小组，按加入时间排列
    @Column(name = "groups")
    private String groups;

    // 粉丝们的uid
    @Column(name = "follower")
    private String follower;

    // 好友们的uid
    @Column(name = "following")
    private String following;

    // 萌邮未读数
    @Column(name = "msg")
    private String msg;

    // 个人介绍
    @Column(name = "about")
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
}
