package com.minami.android.wakemeapp.Config;

/**
 * Created by Minami on 2018/07/26.
 */

public enum UserInfo {
    ID("id"),
    NAME("name"),
    EMAIL("email"),
    CHAT_ROOMS("chatRooms"),
    FRIENDS_ID_LIST("friendsIdList");

    private String label;
    UserInfo(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
