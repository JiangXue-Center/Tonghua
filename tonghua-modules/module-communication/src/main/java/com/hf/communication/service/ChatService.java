package com.hf.communication.service;

import com.hf.communication.enums.MessageType;
import com.hf.communication.model.dto.Message;

public interface ChatService {

//    void saveOfflineMessage(Message message, MessageType type);

    void saveMessage(Message message, MessageType type);

}
