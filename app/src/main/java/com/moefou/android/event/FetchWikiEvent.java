package com.moefou.android.event;

public class FetchWikiEvent {

    private boolean success;

    private boolean isInit;

    private String wikiType;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isInit() {
        return isInit;
    }

    public String getWikiType() {
        return wikiType;
    }

    public FetchWikiEvent(boolean isInit, String wikiType) {
        this.isInit = isInit;
        this.wikiType = wikiType;
    }

    public FetchWikiEvent(boolean success, boolean isInit, String wikiType) {
        this.success = success;
        this.isInit = isInit;
        this.wikiType = wikiType;
    }
}
