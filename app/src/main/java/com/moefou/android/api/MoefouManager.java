package com.moefou.android.api;


import com.moefou.android.object.user.UserResponse;

import rx.Observable;

public interface MoefouManager {

    public Observable<UserResponse> getCurrentUser();

    public Observable<UserResponse> getUserByUid(int uid);

    public Observable<UserResponse> getUserByName(String userName);

}
