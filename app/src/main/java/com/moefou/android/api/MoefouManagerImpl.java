package com.moefou.android.api;


import com.moefou.android.object.user.UserResponse;

import org.scribe.model.Verb;

import rx.Observable;

public class MoefouManagerImpl implements MoefouManager {

    private String accessToken;

    private String accessTokenSecret;

    private static MoefouManagerImpl moefouManager;

    public static MoefouManagerImpl getInstance() {
        if (null == moefouManager) {
            moefouManager = new MoefouBuilder().build();
        }

        return moefouManager;
    }

    private MoefouManagerImpl() {
    }

    public MoefouManagerImpl(MoefouBuilder builder) {
        accessTokenSecret = builder.accessTokenSecret;
        accessToken = builder.accessToken;
    }

    @Override
    public Observable<UserResponse> getCurrentUser() {
        MoefouApi api = RestClient.getService(MoefouApi.class, Verb.GET, "/user/detail.json");
        return api.getCurrentUser();
    }

    @Override
    public Observable<UserResponse> getUserByUid(int uid) {
        return null;
    }

    @Override
    public Observable<UserResponse> getUserByName(String userName) {
        return null;
    }
}
