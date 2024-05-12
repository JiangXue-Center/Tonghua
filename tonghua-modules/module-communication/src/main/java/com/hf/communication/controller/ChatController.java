package com.hf.communication.controller;

import com.hf.cache.service.RedisService;
import com.hf.communication.enums.MessageType;
import com.hf.communication.model.dto.Message;
import com.hf.communication.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import static com.hf.cache.constants.RedisConstant.ONLINE_USER_SET_KEY;

@Controller
public class ChatController {

    private final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ChatService chatService;


    @MessageMapping("/chat")
    public void sendMessage(@Payload Message message) {
        logger.info("chat入参 message {}", message);
//        String senderUsername = principal.getName();
        String recipientUserId = message.getRecipient();
        // 处理消息，可以根据需要存储到数据库，然后发送给指定用户
        chatService.saveMessage(message, message.getType());
        // 例如，将消息发送给指定用户的方法
        if (redisService.isMember(ONLINE_USER_SET_KEY, recipientUserId)) {
            messagingTemplate.convertAndSendToUser(message.getRecipient(), "/messages", message.getContent());
        }
    }

}
