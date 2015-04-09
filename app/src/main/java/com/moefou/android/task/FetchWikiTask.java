package com.moefou.android.task;

import com.activeandroid.ActiveAndroid;
import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchWikiEvent;
import com.moefou.android.object.wiki.Wiki;
import com.moefou.android.object.wiki.WikiMeta;
import com.moefou.android.object.wiki.WikiResponse;
import com.moefou.android.util.SafeAsyncTask;

import java.util.List;


public class FetchWikiTask extends SafeAsyncTask {

    @Override
    public Object call() throws Exception {
        WikiResponse wikiResponse = MoefouManagerImpl.getInstance().getWikiList("music");
        List<Wiki> wikiList = wikiResponse.getResponse().getWikis();
        ActiveAndroid.beginTransaction();
        try {
            for (Wiki wiki : wikiList) {
                wiki.getWiki_cover().save();
                for (WikiMeta wikiMeta : wiki.getWiki_meta()) {
                    wikiMeta.save();
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
        BusProvider.getInstance().post(new FetchWikiEvent());
    }
}
