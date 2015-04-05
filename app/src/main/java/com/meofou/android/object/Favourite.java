package com.meofou.android.object;

public class Favourite {

    // 收藏id号，不同大类的对象的fav_id是可重叠的
    private int fav_id;

    // 被收藏的对象的id
    private int fav_obj_id;

    // 被收藏的对象的类型
    private String fav_obj_type;

    // 执行收藏的用户
    private int fav_uid;

    // 收藏的时间
    private long fav_date;

    // 收藏类型
    private long fav_type;

    // 被收藏的对象，本例中应为条目对象；在某些场景中此项可能不存在
    private Object obj;

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

    public long getFav_type() {
        return fav_type;
    }

    public void setFav_type(long fav_type) {
        this.fav_type = fav_type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
