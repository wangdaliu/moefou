package com.moefou.android.task;

import com.activeandroid.query.Delete;
import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchUserEvent;
import com.moefou.android.object.user.User;
import com.moefou.android.object.user.UserResponse;
import com.moefou.android.util.SafeAsyncTask;


public class FetchUserTask extends SafeAsyncTask {

    @Override
    public Object call() throws Exception {
        UserResponse userResponse = MoefouManagerImpl.getInstance().getCurrentUser();
        new Delete().from(User.class).execute();
        User user = userResponse.getResponse().getUser();
        user.getUser_avatar().save();
        user.save();
        return null;
    }

    @Override
    protected void onFinally() throws RuntimeException {
        super.onFinally();
        BusProvider.getInstance().post(new FetchUserEvent());
    }
}
