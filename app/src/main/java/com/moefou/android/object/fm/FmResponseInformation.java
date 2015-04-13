package com.moefou.android.object.fm;

public class FmResponseInformation {

    private FmParameters parameters;

    private String[] msg;

    private boolean has_error;

    private int error;

    private String request;

    private int page;

    private int item_count;

    private boolean is_target;

    private boolean may_have_next;

    private String next_url;

    public FmParameters getParameters() {
        return parameters;
    }

    public void setParameters(FmParameters parameters) {
        this.parameters = parameters;
    }

    public String[] getMsg() {
        return msg;
    }

    public void setMsg(String[] msg) {
        this.msg = msg;
    }

    public boolean isHas_error() {
        return has_error;
    }

    public void setHas_error(boolean has_error) {
        this.has_error = has_error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public boolean is_target() {
        return is_target;
    }

    public void setIs_target(boolean is_target) {
        this.is_target = is_target;
    }

    public boolean isMay_have_next() {
        return may_have_next;
    }

    public void setMay_have_next(boolean may_have_next) {
        this.may_have_next = may_have_next;
    }

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }
}
