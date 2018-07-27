package com.minami.android.wakemeapp.Model;

import java.util.Date;

/**
 * Created by Minami on 2018/07/27.
 */

public class Text {
    private String id;
    private User sender;
    private String text_content;
    private Date createdAt;
    private Message.Image image;



    public Text() {
    }

    public Text(String id, User sender, String text_content) {
        this.id = id;
        this.sender = sender;
        this.text_content = text_content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Message.Image getImage() {
        return image;
    }

    public void setImage(Message.Image image) {
        this.image = image;
    }
}
