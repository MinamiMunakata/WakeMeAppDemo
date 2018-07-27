package com.minami.android.wakemeapp.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minami on 2018/07/24.
 */

public class ChatRoom {
    private String id;
    private List<User> member;
    private List<Message> messages;

    public ChatRoom() {
    }

    public ChatRoom(String id, List<User> member) {
        this.id = id;
        this.member = member;
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
        for (User u: member){
            participants += " : " + u;
        }
        return participants;
    }
}
