package com.meofou.android.api;

import com.meofou.android.object.User;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface MoefouApi {

    @GET("/user/detail.json")
    Observable<User> getCurrentUser();

    @GET("/user/detail.json")
    Observable<User> getUserByUid(
            @Query("uid") int uid
    );

    @GET("/user/detail.json")
    Observable<User> getUserByName(
            @Query("user_name") int userName
    );
}
