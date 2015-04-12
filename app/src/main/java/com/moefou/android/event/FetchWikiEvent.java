package com.moefou.android.event;

public class FetchWikiEvent {

    private boolean isInit;

    private String wikiType;

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
}
