package com.moefou.android.task;

import com.moefou.android.Const;
import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.core.FmManager;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchPlaylistEvent;
import com.moefou.android.object.fm.FmResponse;
import com.moefou.android.util.SafeAsyncTask;
import com.moefou.android.util.SharedPreferenceUtil;

public class FetchPlaylistTask extends SafeAsyncTask {

    private static final int PERPAGE = 20;

    private static final String MY_RADIO_PAGE = "my_radio_page";

    private boolean mReload;

    public FetchPlaylistTask(boolean reload) {
        mReload = reload;
    }

    @Override
    public Object call() throws Exception {
        if (mReload) {
            SharedPreferenceUtil.saveInt(Const.USER_INFO_FILE, MY_RADIO_PAGE, 1);
        }
        int page = SharedPreferenceUtil.getInt(Const.USER_INFO_FILE, MY_RADIO_PAGE, 1);
        FmResponse response = MoefouManagerImpl.getInstance().getPlaylist(page, PERPAGE);
        if (null == response || null == response.getResponse() || null == response.getResponse().getPlaylist()) {
            return null;
        }

        if (mReload) {
            FmManager.getInstance().deletePlayLists();
        }
        SharedPreferenceUtil.saveInt(Const.USER_INFO_FILE, MY_RADIO_PAGE, ++page);
        FmManager.getInstance().savePlayLists(response.getResponse().getPlaylist());
        return "success";
    }

    @Override
    protected void onSuccess(Object o) throws Exception {
        super.onSuccess(o);
        BusProvider.getInstance().post(new FetchPlaylistEvent(o == null ? false : true));
    }

}
