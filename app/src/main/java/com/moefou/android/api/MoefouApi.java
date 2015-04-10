package com.moefou.android.api;


import com.moefou.android.object.user.UserResponse;
import com.moefou.android.object.wiki.WikiResponse;

import retrofit.http.GET;
import retrofit.http.Query;

public interface MoefouApi {

    @GET("/user/detail.json")
    UserResponse getCurrentUser();

    @GET("/user/detail.json")
    UserResponse getUserByUid(
            @Query("uid") int uid
    );

    @GET("/user/detail.json")
    UserResponse getUserByName(
            @Query("user_name") int userName
    );

    @GET("/wikis.json")
    WikiResponse getWikiList(
            @Query("wiki_type") String wikiType
    );
}
