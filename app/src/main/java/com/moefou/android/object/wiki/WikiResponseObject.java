package com.moefou.android.object.wiki;

import java.util.List;

public class WikiResponseObject {

    private WikiResponseInformation information;

    private List<Wiki> wikis;

    public List<Wiki> getWikis() {
        return wikis;
    }

    public void setWikis(List<Wiki> wikis) {
        this.wikis = wikis;
    }
}
