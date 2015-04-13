package com.moefou.android.task;

import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.util.SafeAsyncTask;

public class FetchRelationshipTask extends SafeAsyncTask {

    private int wikiId;

    public FetchRelationshipTask(int wikiId) {
        this.wikiId = wikiId;
    }

    @Override
    public Object call() throws Exception {
        MoefouManagerImpl.getInstance().getRelationships(wikiId, "song");
        return null;
    }

}
