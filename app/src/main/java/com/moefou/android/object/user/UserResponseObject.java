package com.moefou.android.object.user;

public class UserResponseObject {

    private UserResponseInformation information;

    private User user;

    public UserResponseInformation getInformation() {
        return information;
    }

    public void setInformation(UserResponseInformation information) {
        this.information = information;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
