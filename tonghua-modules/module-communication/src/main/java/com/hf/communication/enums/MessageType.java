package com.hf.communication.enums;

public enum MessageType {

    TEXT("text"),
    VOICE("voice"),
    IMAGE("image"),
    VIDEO("video"),
    FILE("file");

    private final String type;

    MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
