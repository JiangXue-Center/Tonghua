package com.hf.communication.model.vo;

import com.hf.communication.enums.MessageStatus;
import com.hf.communication.enums.MessageType;
import org.springframework.data.annotation.Id;

public class MessageVO {

    private String content;

    private String senderId;

    private String senderName;

    private String avatarUrl;

    private Long messageTime;

    private MessageType type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
