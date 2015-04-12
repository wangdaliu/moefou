package com.moefou.android.task;

import com.moefou.android.Const;
import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.core.WikiManager;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchWikiEvent;
import com.moefou.android.object.wiki.WikiResponse;
import com.moefou.android.util.SafeAsyncTask;
import com.moefou.android.util.SharedPreferenceUtil;


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
        if (null == wikiResponse || null == wikiResponse.getResponse().getWikis()) {
            return null;
        }
        WikiManager.getInstance().saveWikiList(wikiResponse.getResponse().getWikis());

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
        return null;
    }

    @Override
    protected void onFinally() throws RuntimeException {
        super.onFinally();
        BusProvider.getInstance().post(new FetchWikiEvent(false, mWikiType));
    }
}
