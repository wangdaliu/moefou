package com.moefou.android.event;

import com.moefou.android.object.fm.PlayList;

public class PlayMusicEvent {

    public PlayList mPlayList;

    public PlayMusicEvent(PlayList playList) {
        mPlayList = playList;
    }
}
