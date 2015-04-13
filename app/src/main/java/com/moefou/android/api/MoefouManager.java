package com.moefou.android.api;


import com.moefou.android.object.user.UserResponse;
import com.moefou.android.object.wiki.WikiResponse;

import rx.Observable;

public interface MoefouManager {

    public UserResponse getCurrentUser();

    public UserResponse getUserByUid(int uid);

    public UserResponse getUserByName(String userName);

    public WikiResponse getWikiList(String wikiType, int page, int perpage);

    public void getRelationships(int wikiId, String objType);

}
