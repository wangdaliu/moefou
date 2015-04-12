package com.moefou.android.core;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.moefou.android.Application;
import com.moefou.android.object.user.Icon;
import com.moefou.android.object.user.User;
import com.moefou.android.provider.MoeTables.TIcon;
import com.moefou.android.provider.MoeTables.TUser;

public class UserManager {

    private static UserManager mUserManager = new UserManager();

    public static UserManager getInstance() {
        return mUserManager;
    }

    public void saveUser(User user) {
        ContentResolver resolver = Application.getInstance().getContentResolver();
        // save user
        Uri userUri = resolver.insert(TUser.CONTENT_URI, user.toContentValues());
        // save icon
        long id = Long.parseLong(userUri.getLastPathSegment());
        user.getUser_avatar().setFkUserId(id);
        resolver.insert(TIcon.CONTENT_URI, user.getUser_avatar().toContentValues());
    }

    public void deleteCurrentUser() {
        ContentResolver resolver = Application.getInstance().getContentResolver();
        resolver.delete(TUser.CONTENT_URI, null, null);
    }

    public User getCurrentUser() {
        ContentResolver resolver = Application.getInstance().getContentResolver();
        // query user
        Cursor userCursor = null;
        User user = null;
        try {
            userCursor = resolver.query(TUser.CONTENT_URI, null, null, null, null);
            if (null != userCursor && userCursor.moveToFirst()) {
                user = new User(userCursor);
            }
        } catch (Exception e) {

        } finally {
            if (null != userCursor) {
                userCursor.close();
            }
        }
        if (null != user) {
            Cursor iconCursor = null;
            try {
                iconCursor = resolver.query(TIcon.CONTENT_URI, null, null, null, null);
                if (null != iconCursor && iconCursor.moveToFirst()) {
                    Icon icon = new Icon(iconCursor);
                    user.setUser_avatar(icon);
                }
            } catch (Exception e) {

            } finally {
                if (null != iconCursor) {
                    iconCursor.close();
                }
            }
        }
        return user;
    }
}
