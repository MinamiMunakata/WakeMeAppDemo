package com.minami.android.wakemeapp.Model;

import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Minami on 2018/07/19.
 */

public class User implements IUser {

    private String id;
    private String name;
    private String avator;
    private String email;
    private List<ChatRoom> chatRooms;
    private List<User> friendsList = new ArrayList<>();

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

    public List<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void setChatRooms(List<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public void addChatRoom(ChatRoom chatRoom) {
        chatRooms.add(chatRoom);
    }

    public List<User> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<User> friendsList) {
        this.friendsList = friendsList;
    }

    public void addFriendToList(User friend){
        if (friendsList.size() > 0) {
            if (!friendsList.contains(friend)){
                friendsList.add(friend);
            }
        } else {
            friendsList.add(friend);
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        if (this.getId().equals(((User)obj).getId())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return email.length() * 37;
    }
}
