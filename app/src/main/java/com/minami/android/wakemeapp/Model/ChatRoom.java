package com.minami.android.wakemeapp.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minami on 2018/07/24.
 */

public class ChatRoom {
    private static final String TAG = "ChatRoom";
    private String id;
    private List<User> member = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();
    public static final String CHAT_ROOM_ID = "ChatRoomId";

    public ChatRoom() {
    }

    public ChatRoom(String id, List<User> member) {
        this.id = id;
        this.member = member;
    }

    public ChatRoom(String id, List<User> member, List<Message> messages) {
        this.id = id;
        this.member = member;
        this.messages = messages;
    }
    public boolean containsCurrentUser(String currentUserId){
        for (User user: member){
            if (user.getId().equals(currentUserId)){
                return true;
            }
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getMember() {
        return member;
    }

    public void setMember(List<User> member) {
        this.member = member;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        String participants = "";
        for (User user : member) {
            participants += " : " + user;
        }
        return participants;
    }
}
