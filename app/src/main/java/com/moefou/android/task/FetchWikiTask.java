package com.moefou.android.task;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchWikiEvent;
import com.moefou.android.object.wiki.Wiki;
import com.moefou.android.object.wiki.WikiMeta;
import com.moefou.android.object.wiki.WikiResponse;
import com.moefou.android.util.SafeAsyncTask;

import java.util.List;


public class FetchWikiTask extends SafeAsyncTask {

    private String mWikiType;

    public FetchWikiTask(String wikiType) {
        mWikiType = wikiType;
    }

    @Override
    public Object call() throws Exception {
        WikiResponse wikiResponse = MoefouManagerImpl.getInstance().getWikiList(mWikiType);
        List<Wiki> wikiList = wikiResponse.getResponse().getWikis();

        List<Wiki> wikiSavedList = new Select().from(Wiki.class).execute();

        if (null == wikiList) {
            return null;
        }
        ActiveAndroid.beginTransaction();
        try {
            for (Wiki wiki : wikiList) {
                if (isWikiExist(wiki.getWiki_id(), wikiSavedList)) {
                    continue;
                }
                if (null != wiki.getWiki_cover()) {
                    wiki.getWiki_cover().save();
                }
                if (null != wiki.getWiki_user_fav()) {
                    wiki.getWiki_user_fav().save();
                }
                if (null != wiki.getWiki_meta()) {
                    for (WikiMeta wikiMeta : wiki.getWiki_meta()) {
                        wikiMeta.save();
                    }
                }
                wiki.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return null;
    }

    @Override
    protected void onFinally() throws RuntimeException {
        super.onFinally();
        BusProvider.getInstance().post(new FetchWikiEvent(mWikiType));
    }

    private boolean isWikiExist(long wikiId, List<Wiki> wikiSavedList) {
        for (Wiki wiki : wikiSavedList) {
            if (wiki.getWiki_id() == wikiId) {
                return true;
            }
        }
        return false;
    }
}
