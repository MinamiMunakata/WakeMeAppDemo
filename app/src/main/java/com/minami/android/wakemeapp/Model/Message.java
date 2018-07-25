package com.minami.android.wakemeapp.Model;

import android.support.annotation.Nullable;

import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.MessageContentType;

import java.util.Date;

/**
 * Created by Minami on 2018/07/19.
 */

public class Message implements IMessage, MessageContentType.Image {

    private String id;
    private String text;
    private Date createdAt;
    private User user;
    private Image image;

    public Message() {
    }

    public Message(String id, User user, String text) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.createdAt = new Date();
    }

    public Message(String id, Author user, String text, Date createdAt) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.createdAt = createdAt;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    @Nullable
    @Override
    public String getImageUrl() {
        return image == null ? null : image.url;
    }

    public String getStatus() {
        return "Sent";
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public static class Image {

        private String url;

        public Image(String url) {
            this.url = url;
        }
    }
}
