package com.moefou.android.task;

import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.object.fm.FmResponse;
import com.moefou.android.util.SafeAsyncTask;

public class FetchPlaylistTask extends SafeAsyncTask {
    @Override
    public Object call() throws Exception {
        FmResponse response = MoefouManagerImpl.getInstance().getPlaylist();

        return null;
    }

}
