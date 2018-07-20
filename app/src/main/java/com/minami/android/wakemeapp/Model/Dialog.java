package com.minami.android.wakemeapp.Model;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.ArrayList;

/**
 * Created by Minami on 2018/07/19.
 */

public class Dialog implements IDialog {
    private String id;
    private String dialogPhoto;
    private String dialogName;
    private ArrayList<User> users;
    private Message lastMessage;
    private int unreadCount;

    public Dialog(String id, String name, String photo,
                  ArrayList<User> users, Message lastMessage, int unreadCount) {

        this.id = id;
        this.dialogName = name;
        this.dialogPhoto = photo;
        this.users = users;
        this.lastMessage = lastMessage;
        this.unreadCount = unreadCount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return dialogPhoto;
    }

    @Override
    public String getDialogName() {
        return dialogName;
    }

    @Override
    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public Message getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLastMessage(IMessage lastmessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
