package com.moefou.android.object.fm;

import java.util.List;

public class FmResponseObject {

    private FmResponseInformation information;

    private List<PlayList> playlist;

    public FmResponseInformation getInformation() {
        return information;
    }

    public void setInformation(FmResponseInformation information) {
        this.information = information;
    }

    public List<PlayList> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<PlayList> playlist) {
        this.playlist = playlist;
    }
}
