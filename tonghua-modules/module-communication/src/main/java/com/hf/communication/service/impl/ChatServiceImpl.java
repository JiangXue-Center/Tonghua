package com.hf.communication.service.impl;

import com.hf.communication.dao.ChatMessageRepository;
import com.hf.communication.enums.MessageStatus;
import com.hf.communication.enums.MessageType;
import com.hf.communication.model.dto.Message;
import com.hf.communication.model.entity.ChatMessage;
import com.hf.communication.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

//    @Override
//    public void saveOfflineMessage(Message message, MessageType type) {
//        ChatMessage chatMessage = new ChatMessage();
//        chatMessage.setContent(message.getContent());
//        chatMessage.setReceiver(message.getRecipient());
//        chatMessage.setSender(message.getSender());
//        chatMessage.setType(type);
//        chatMessage.setStatus(MessageStatus.NOT_READ);
//        mongoTemplate.save(chatMessage);
//    }

    @Override
    public void saveMessage(Message message, MessageType type) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(message.getContent());
        chatMessage.setReceiver(message.getRecipient());
        chatMessage.setSender(message.getSender());
        chatMessage.setType(type);
        chatMessage.setStatus(MessageStatus.NOT_READ);
        long l = new Date().getTime();
        chatMessage.setMessageTime(l);
        logger.info("message {} 发送及保存时间 {}", chatMessage, l);
        mongoTemplate.save(chatMessage);
    }

}
