package com.minami.android.wakemeapp.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minami on 2018/07/24.
 */

public class Room {
    private String id;
    private ArrayList<User> member;
    private List<Message> messages;

    public Room() {
    }

    public Room(String id, ArrayList<User> member) {
        this.id = id;
        this.member = member;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<User> getMember() {
        return member;
    }

    public void setMember(ArrayList<User> member) {
        this.member = member;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
