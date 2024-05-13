package com.hf.communication.model.vo;

import com.hf.communication.enums.MessageType;

import java.io.Serializable;
import java.util.Date;

public class MessageListItemVO implements Serializable {

    private String userId;

    private String username;

    private String avatar;

    private Integer unReadMessageNumber;

    private String lastMessageContent;

    private MessageType messageType;

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

    public Integer getUnReadMessageNumber() {
        return unReadMessageNumber;
    }

    public void setUnReadMessageNumber(Integer notReadMessageNumber) {
        this.unReadMessageNumber = notReadMessageNumber;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setLastMessageContent(String lastMessageContent) {
        this.lastMessageContent = lastMessageContent;
    }

    public String getLastMessageContent() {
        return lastMessageContent;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Date getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(Date lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }
}
