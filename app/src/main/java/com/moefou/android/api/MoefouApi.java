package com.moefou.android.api;


import com.moefou.android.object.user.ResponseUser;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface MoefouApi {

    @GET("/user/detail.json")
    Observable<ResponseUser> getCurrentUser();

    @GET("/user/detail.json")
    Observable<ResponseUser> getUserByUid(
            @Query("uid") int uid
    );

    @GET("/user/detail.json")
    Observable<ResponseUser> getUserByName(
            @Query("user_name") int userName
    );
}
