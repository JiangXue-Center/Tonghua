package com.hf.communication.model.vo;

import java.io.Serializable;
import java.util.Date;

public class MessageListItemVO implements Serializable {

    private String userId;

    private String username;

    private String avatar;

    private Integer notReadMessageNumber;

    private MessageVO lastMessage;

    private Date lastMessageTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getNotReadMessageNumber() {
        return notReadMessageNumber;
    }

    public void setNotReadMessageNumber(Integer notReadMessageNumber) {
        this.notReadMessageNumber = notReadMessageNumber;
    }

    public MessageVO getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageVO lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Date getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(Date lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }
}
