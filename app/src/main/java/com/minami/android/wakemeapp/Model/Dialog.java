package com.minami.android.wakemeapp.Model;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minami on 2018/07/19.
 */

public class Dialog implements IDialog {
    private String id;
    private String dialogPhoto;
    private String dialogName;
    private List<User> member = new ArrayList<>();
    private IMessage lastMessage;
    private int unreadCount;

    public Dialog() {
    }

    public Dialog(String id, List<User> member) {
        this.id = id;
        this.member = member;
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
        return member;
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
