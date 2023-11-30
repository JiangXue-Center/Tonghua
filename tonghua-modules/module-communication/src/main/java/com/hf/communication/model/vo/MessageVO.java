package com.hf.communication.model.vo;

import com.hf.communication.enums.MessageStatus;
import com.hf.communication.enums.MessageType;
import org.springframework.data.annotation.Id;

public class MessageVO {

    private String content;

    private String sender;

    private Long messageTime;

    private MessageType type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public long getMessageTime() {
        return messageTime;
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
