package com.minami.android.wakemeapp.Model;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Created by Minami on 2018/07/21.
 */

public class Author extends User implements IUser {
    private String id;
    private String name;
    private String avator;
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
        return avator;
    }
}
