package com.moefou.android.api;


import com.moefou.android.object.user.ResponseUser;

import rx.Observable;

public interface MoefouManager {

    public Observable<ResponseUser> getCurrentUser();

    public Observable<ResponseUser> getUserByUid(int uid);

    public Observable<ResponseUser> getUserByName(String userName);

}
