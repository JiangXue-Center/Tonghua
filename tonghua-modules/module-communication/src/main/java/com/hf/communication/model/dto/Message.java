package com.hf.communication.model.dto;

public class Message {

    //消息内容
    private String content;

    //发送者
    private String sender;

    //接收者
    private String recipient;

    //消息类型
    private String type;

    // 构造方法、getter和setter等

    // 注意：你可能还需要提供一个默认构造方法，以便Spring能够正确地反序列化消息。

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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
