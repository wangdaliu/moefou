package com.moefou.android.api;


import com.moefou.android.object.user.UserResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface MoefouApi {

    @GET("/user/detail.json")
    Observable<UserResponse> getCurrentUser();

    @GET("/user/detail.json")
    Observable<UserResponse> getUserByUid(
            @Query("uid") int uid
    );

    @GET("/user/detail.json")
    Observable<UserResponse> getUserByName(
            @Query("user_name") int userName
    );
}
