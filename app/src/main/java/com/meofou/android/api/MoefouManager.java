package com.meofou.android.api;

import com.meofou.android.object.User;

import rx.Observable;

public interface MoefouManager {

    public Observable<User> getCurrentUser();

    public Observable<User> getUserByUid(int uid);

    public Observable<User> getUserByName(String userName);

}
