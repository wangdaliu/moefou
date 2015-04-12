package com.moefou.android.api;


import com.moefou.android.object.user.UserResponse;
import com.moefou.android.object.wiki.WikiResponse;

import org.scribe.model.Verb;

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
    public UserResponse getCurrentUser() {
        MoefouApi api = RestClient.getService(MoefouApi.class, Verb.GET, "/user/detail.json");
        return api.getCurrentUser();
    }

    @Override
    public UserResponse getUserByUid(int uid) {
        return null;
    }

    @Override
    public UserResponse getUserByName(String userName) {
        return null;
    }

    @Override
    public WikiResponse getWikiList(String wikiType, int page, int perpage) {
        MoefouApi api = RestClient.getService(MoefouApi.class, Verb.GET, "/wikis.json?wiki_type=" + wikiType + "&page=" + page + "&perpage=" + perpage);
        WikiResponse response = null;
        try {
            response = api.getWikiList(wikiType, page, perpage);
        } catch (Exception e) {

        }
        return response;
    }
}
