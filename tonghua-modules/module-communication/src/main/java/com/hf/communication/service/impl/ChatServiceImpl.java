package com.hf.communication.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hf.apisystem.api.RemoteUserService;
import com.hf.cache.service.RedisService;
import com.hf.communication.dao.ChatMessageRepository;
import com.hf.communication.enums.MessageStatus;
import com.hf.communication.enums.MessageType;
import com.hf.communication.model.dto.Message;
import com.hf.communication.model.entity.ChatMessage;
import com.hf.communication.model.vo.MessageListItemVO;
import com.hf.communication.model.vo.MessageVO;
import com.hf.communication.service.ChatService;
import com.hf.core.model.entity.user.User;
import com.hf.core.utils.JwtUtil;
import com.hf.core.utils.TokenHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.hf.cache.constants.RedisConstant.USER_MESSAGE_LIST_KEY;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RedisService redisService;

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
        DecodedJWT tokenInfo = JwtUtil.getTokenInfo(message.getSender());
        String userId = tokenInfo.getClaim("id").asString();
        chatMessage.setSender(userId);
        chatMessage.setType(type);
        chatMessage.setStatus(MessageStatus.NOT_READ);
        long l = new Date().getTime();
        chatMessage.setMessageTime(l);
        String key = USER_MESSAGE_LIST_KEY + userId;
        //将接受者的id加入到用户的消息列表集合
        redisService.add2Set(key, message.getRecipient());
        logger.info("message {} 发送及保存时间 {}", chatMessage, l);
        mongoTemplate.save(chatMessage);
    }

    @Override
    public Page<ChatMessage> findPrivateMessages(String friendId, int offset, int size) {
        Pageable pageable = PageRequest.of(
                offset - 1,
                size,
                Sort.by(Sort.Direction.DESC, "messageTime"));

        String userId = TokenHolder.get();
        logger.info("userId {}", userId);
        Query query = new Query();
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("sender").is(userId).and("receiver").is(friendId),
                Criteria.where("sender").is(friendId).and("receiver").is(userId)
        );
//        Criteria criteria = Criteria.where("sender").is(userId).and("receiver").is(friendId);
        query.addCriteria(criteria);
        query.with(pageable);
        List<ChatMessage> chatMessages = mongoTemplate.find(query, ChatMessage.class);
        logger.info("查询结果 {}", chatMessages);
        long count = mongoTemplate.count(query, ChatMessage.class);

        return new PageImpl<>(chatMessages, pageable, count);
    }

    @Override
    public List<MessageListItemVO> findMessageList() {
        String userId = TokenHolder.get();
        String key = USER_MESSAGE_LIST_KEY + userId;
        Set<String> set = redisService.getCacheSet(key);
        List<User> users = remoteUserService.selectSimpleUsers(set);
        List<MessageListItemVO> messageListVOS = new ArrayList<>();
        for (User friend : users) {
            Criteria criteria = new Criteria().orOperator(
                    Criteria.where("sender").is(userId).and("receiver").is(friend.getId()),
                    Criteria.where("sender").is(friend.getId()).and("receiver").is(userId)
            );
            Query query = new Query(criteria);
            query.with(Sort.by(Sort.Direction.DESC, "messageTime"));
            query.limit(1);

            List<ChatMessage> messages = mongoTemplate.find(query, ChatMessage.class);

            long count = countUnreadMessages(userId, friend.getId());
            MessageListItemVO messageListItemVO = new MessageListItemVO();
            messageListItemVO.setUserId(friend.getId());
            messageListItemVO.setUsername(friend.getUsername());
            messageListItemVO.setAvatar(friend.getAvatarUrl());
            messageListItemVO.setNotReadMessageNumber((int) count);

            ChatMessage lastMessage = messages.getFirst();
            MessageVO messageVO = new MessageVO();
            messageVO.setContent(lastMessage.getContent());
            messageVO.setSender(lastMessage.getSender());
            messageVO.setType(lastMessage.getType());
            messageVO.setMessageTime(lastMessage.getMessageTime());

            messageListItemVO.setLastMessage(messageVO);
            messageListItemVO.setLastMessageTime(new Date(lastMessage.getMessageTime()));

            messageListVOS.add(messageListItemVO);
        }
        return messageListVOS;
    }

    public long countUnreadMessages(String userId, String friendId) {
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("sender").is(friendId).and("receiver").is(userId),
                Criteria.where("status").is("NOT_READ")
        );

        Query query = new Query(criteria);
        return mongoTemplate.count(query, ChatMessage.class);
    }

}
