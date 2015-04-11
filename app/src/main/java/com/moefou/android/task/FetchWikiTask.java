package com.moefou.android.task;

import android.os.Handler;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.moefou.android.Application;
import com.moefou.android.Const;
import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchWikiEvent;
import com.moefou.android.object.wiki.Wiki;
import com.moefou.android.object.wiki.WikiResponse;
import com.moefou.android.util.SafeAsyncTask;
import com.moefou.android.util.SharedPreferenceUtil;

import java.util.List;


public class FetchWikiTask extends SafeAsyncTask {

    private static final int PERPAGE = 20;

    private static final String RADIO_PAGE = "radio_page";

    private static final String MUSIC_PAGE = "music_page";

    private String mWikiType;

    public FetchWikiTask(String wikiType) {
        mWikiType = wikiType;
    }

    @Override
    public Object call() throws Exception {
        final List<Wiki> wikiSavedList = new Select().from(Wiki.class).where("wiki_type=?", mWikiType).execute();
        new Handler(Application.getInstance().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                BusProvider.getInstance().post(new FetchWikiEvent(true, mWikiType, wikiSavedList));
            }
        });

        int page = 1;
        switch (mWikiType) {
            case Const.RADIO:
                page = SharedPreferenceUtil.getInt(Const.USER_INFO_FILE, RADIO_PAGE, 1);
                break;
            case Const.MUSIC:
                page = SharedPreferenceUtil.getInt(Const.USER_INFO_FILE, MUSIC_PAGE, 1);
                break;
            default:
                break;
        }

        WikiResponse wikiResponse = MoefouManagerImpl.getInstance().getWikiList(mWikiType, page++, PERPAGE);
        final List<Wiki> wikiList = wikiResponse.getResponse().getWikis();

        if (null == wikiList) {
            return null;
        }
        ActiveAndroid.beginTransaction();
        try {
            for (Wiki wiki : wikiList) {
                if (null != wiki.getWiki_cover()) {
                    wiki.getWiki_cover().save();
                }
                if (null != wiki.getWiki_user_fav()) {
                    wiki.getWiki_user_fav().save();
                }
                wiki.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }

        switch (mWikiType) {
            case Const.RADIO:
                SharedPreferenceUtil.saveInt(Const.USER_INFO_FILE, RADIO_PAGE, page++);
                break;
            case Const.MUSIC:
                SharedPreferenceUtil.saveInt(Const.USER_INFO_FILE, MUSIC_PAGE, page++);
                break;
            default:
                break;
        }

        new Handler(Application.getInstance().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                BusProvider.getInstance().post(new FetchWikiEvent(false, mWikiType, wikiList));
            }
        });
        return null;
    }
}
