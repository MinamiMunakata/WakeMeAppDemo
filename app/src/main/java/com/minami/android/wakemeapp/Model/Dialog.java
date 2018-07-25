package com.minami.android.wakemeapp.Model;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minami on 2018/07/19.
 */

public class Dialog implements IDialog {
    private String id;
    private String dialogPhoto;
    private String dialogName;
    private List<User> users;
    private IMessage lastMessage;
    private int unreadCount;

    public Dialog() {
    }

    public Dialog(String id, List<User> users) {
        this.id = id;
        this.users = users;
    }
    public Dialog(String id, String name, String photo,
                  List<User> users, IMessage lastMessage, int unreadCount) {

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
    public List<User> getUsers() {
        return users;
    }

    @Override
    public IMessage getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLastMessage(IMessage lastMessage) {
        this.lastMessage = lastMessage;

    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }

}
