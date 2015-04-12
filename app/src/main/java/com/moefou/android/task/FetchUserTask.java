package com.moefou.android.task;

import com.moefou.android.api.MoefouManagerImpl;
import com.moefou.android.core.UserManager;
import com.moefou.android.event.BusProvider;
import com.moefou.android.event.FetchUserEvent;
import com.moefou.android.object.user.User;
import com.moefou.android.object.user.UserResponse;
import com.moefou.android.util.SafeAsyncTask;


public class FetchUserTask extends SafeAsyncTask {

    @Override
    public Object call() throws Exception {
        UserResponse userResponse = MoefouManagerImpl.getInstance().getCurrentUser();

        UserManager.getInstance().deleteCurrentUser();

        User user = userResponse.getResponse().getUser();
        UserManager.getInstance().saveUser(user);
        return null;
    }

    @Override
    protected void onFinally() throws RuntimeException {
        super.onFinally();
        BusProvider.getInstance().post(new FetchUserEvent());
    }
}
