package com.minami.android.wakemeapp.Model;

import android.util.Log;

import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.firebase.ui.auth.ui.email.CheckEmailFragment.TAG;

/**
 * Created by Minami on 2018/07/19.
 */

public class User implements IUser {
    private String id;
    private String name;
    private String avator;
    private String email;
    private List<String> chatRoomsIdList = new ArrayList<>();
    private List<String> friendsIdList = new ArrayList<>();

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

    public List<String> getChatRoomsIdList() {
        return chatRoomsIdList;
    }

    public void setChatRoomsIdList(List<String> chatRoomsIdList) {
        this.chatRoomsIdList = chatRoomsIdList;
    }

    public void addChatRoom(String newChatRoomId) {
        List<String> updatedChatRoomIdList = new ArrayList<>();
        if (chatRoomsIdList.size() > 0) {
            HashSet<String> set = new HashSet<>();
            for (String id : chatRoomsIdList) {
                if (!set.contains(id)) {
                    set.add(id);
                    updatedChatRoomIdList.add(id);
                    Log.i(TAG, "addChatRoom: -----------1");
                }
            }
            if (!set.contains(newChatRoomId)){
                updatedChatRoomIdList.add(newChatRoomId);
            }
        } else {
            chatRoomsIdList.add(newChatRoomId);
        }
    }

    public List<String> getFriendsIdList() {
        return friendsIdList;
    }

    public void setFriendsIdList(List<String> friendsIdList) {
        this.friendsIdList = friendsIdList;
    }

    public void addFriendToList(String newFriendId){
        List<String> updatedFriendsIdList = new ArrayList<>();
        if (friendsIdList.size() > 0) {
            HashSet<String> set = new HashSet<>();
            for (String id: friendsIdList){
                if (!set.contains(id)){
                    set.add(id);
                    updatedFriendsIdList.add(id);
                    Log.i(TAG, "addFriendToList: addddddd");
                }
            }
            if (!set.contains(newFriendId) && !newFriendId.equals(this.id)){
                updatedFriendsIdList.add(newFriendId);
                setFriendsIdList(updatedFriendsIdList);
            }
        } else {

            friendsIdList.add(newFriendId);

            Log.i(TAG, "addFriendToList: " + newFriendId);
            Log.i(TAG, "addFriendToList: " + this.id);
            Log.i(TAG, "addFriendToList: " + friendsIdList);
            Log.i(TAG, "addFriendToList: heeeeeeeeeeeeeeey");
        }

    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
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
