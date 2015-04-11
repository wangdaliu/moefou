package com.moefou.android.event;

import com.moefou.android.object.wiki.Wiki;

import java.util.List;

public class FetchWikiEvent {

    private boolean isInit;

    private String wikiType;

    private List<Wiki> wikiList;

    public boolean isInit() {
        return isInit;
    }

    public List<Wiki> getWikiList() {
        return wikiList;
    }

    public String getWikiType() {
        return wikiType;
    }

    public FetchWikiEvent(boolean isInit, String wikiType, List<Wiki> wikiList) {
        this.isInit = isInit;
        this.wikiType = wikiType;
        this.wikiList = wikiList;
    }
}
