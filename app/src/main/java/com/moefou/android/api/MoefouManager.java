package com.moefou.android.api;


import com.moefou.android.object.fm.FmResponse;
import com.moefou.android.object.user.UserResponse;
import com.moefou.android.object.wiki.WikiResponse;

public interface MoefouManager {

    public UserResponse getCurrentUser();

    public UserResponse getUserByUid(int uid);

    public UserResponse getUserByName(String userName);

    public WikiResponse getWikiList(String wikiType, int page, int perpage);

    // TODO test failed.
    public void getRelationships(int wikiId, String objType);

    public FmResponse getPlaylist(int page, int perpage);

}
