package com.moefou.android.object.wiki;

public class WikiResponseInformation {

    private WikiParameters parameters;

    private String[] msg;

    private boolean has_error;

    private String request;

    private int page;

    private int perpage;

    private int count;

    public WikiParameters getParameters() {
        return parameters;
    }

    public void setParameters(WikiParameters parameters) {
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

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
