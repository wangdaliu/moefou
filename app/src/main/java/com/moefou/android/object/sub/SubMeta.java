package com.moefou.android.object.sub;

public class SubMeta {

    // 字段名
    private String meta_key;

    // 字段值
    private String meta_value;

    // 字段类型，1为小字段，2为大字段
    private int meta_type;

    public String getMeta_key() {
        return meta_key;
    }

    public void setMeta_key(String meta_key) {
        this.meta_key = meta_key;
    }

    public String getMeta_value() {
        return meta_value;
    }

    public void setMeta_value(String meta_value) {
        this.meta_value = meta_value;
    }

    public int getMeta_type() {
        return meta_type;
    }

    public void setMeta_type(int meta_type) {
        this.meta_type = meta_type;
    }
}
