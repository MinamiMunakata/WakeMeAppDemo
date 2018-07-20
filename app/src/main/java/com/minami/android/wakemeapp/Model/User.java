package com.minami.android.wakemeapp.Model;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by Minami on 2018/07/19.
 */

public class User implements IUser {

    private String id;
    private String name;
    private String avatar;
    private boolean online;

    public User(String id, String name, String avatar, boolean online) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return null;
    }

    public boolean isOnline() {
        return online;
    }
}
