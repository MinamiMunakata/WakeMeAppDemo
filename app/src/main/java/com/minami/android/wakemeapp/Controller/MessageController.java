package com.minami.android.wakemeapp.Controller;

import com.minami.android.wakemeapp.Model.Message;
import com.minami.android.wakemeapp.Model.User;

import java.util.Date;

import static com.minami.android.wakemeapp.Controller.DBController.MESSAGE_REF;

/**
 * Created by Minami on 2018/07/27.
 */

public class MessageController {

    public static void archiveMessage(String chatRoomId, Message message){
        Archive archive = new Archive(message.getId(),message.getText(),
                message.getCreatedAt(),message.getUser(),message.getImage());
        String mId = MESSAGE_REF.child(chatRoomId).push().getKey();
        MESSAGE_REF.child(chatRoomId).child(mId).setValue(archive);
    }

    public static Message convertToMessageFromArchive(Archive archive){
        Message message = new Message(archive.getId(),archive.getUser(),archive.getText(),archive.getCreatedAt());
        return message;
    }

    public static class Archive {
        private String id;
        private String text;
        private Date createdAt;
        private User user;
        private Message.Image image;

        public Archive() {
        }

        public Archive(String id, String text, Date createdAt, User user, Message.Image image) {
            this.id = id;
            this.text = text;
            this.createdAt = createdAt;
            this.user = user;
            this.image = image;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Message.Image getImage() {
            return image;
        }

        public void setImage(Message.Image image) {
            this.image = image;
        }
        public String getStatus() {
            return "Sent";
        }
    }
}



