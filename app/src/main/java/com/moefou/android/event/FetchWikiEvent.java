package com.moefou.android.event;

public class FetchWikiEvent {

    private String wikiType;

    public String getWikiType() {
        return wikiType;
    }

    public FetchWikiEvent(String wikiType) {
        this.wikiType = wikiType;
    }
}
