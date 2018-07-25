package com.minami.android.wakemeapp.Model;

import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Minami on 2018/07/19.
 */

public class User implements IUser {

    private String id;
    private String name;
    private String avator;
    private String email;
//    private HashSet<User> friends;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
//        friends = new HashSet<>();
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avator;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //    public HashSet<User> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(HashSet<User> friends) {
//        this.friends = friends;
//    }
//
//    public void addFriends(User user) {
//        friends.add(user);
//    }
}
