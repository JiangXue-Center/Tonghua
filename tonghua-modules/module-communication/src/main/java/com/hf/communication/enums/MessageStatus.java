package com.hf.communication.enums;

public enum MessageStatus {

    NOT_READ(0),
    READED(1);


    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    MessageStatus(int status) {
        this.status = status;
    }

}
