package com.moefou.android.object.user;

import com.moefou.android.object.ResponseInformation;

public class UserResponseObject {

    private ResponseInformation information;

    private User user;

    public ResponseInformation getInformation() {
        return information;
    }

    public void setInformation(ResponseInformation information) {
        this.information = information;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
