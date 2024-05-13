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
import com.hf.core.model.vo.SimpleUser;
import com.hf.core.utils.JwtUtil;
import com.hf.core.utils.TokenHolder;
import com.hf.minio.service.MinIOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.hf.cache.constants.RedisConstant.USER_BASE_INFO_KEY;
import static com.hf.cache.constants.RedisConstant.USER_MESSAGE_LIST_KEY;

@Service
public class ChatServiceImpl implements ChatService {

//    @Autowired
//    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MinIOService minIOService;

    private final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    private final static ExecutorService MESSAGE_POOL_EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();

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

    /**
     *
     * @param message 消息体
     * @param type 消息类型
     * @description 将websocket中发送的信息保存到mongodb
     */
    @Override
    public void saveMessage(Message message, String type) {
//        String sender = message.getSender();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(message.getContent());
        chatMessage.setReceiver(message.getRecipient());
        //将token解析为userId
        DecodedJWT tokenInfo = JwtUtil.getTokenInfo(message.getSender());
        String userId = tokenInfo.getClaim("id").asString();
        chatMessage.setSender(userId);
        //todo 将相对应的类型消息做处理
        chatMessage.setType(MessageType.valueOf(type.toUpperCase()));
        chatMessage.setStatus(MessageStatus.NOT_READ);
        long l = new Date().getTime();
        chatMessage.setMessageTime(l);
        String key = USER_MESSAGE_LIST_KEY + userId;
        //将接受者的id加入到用户的消息列表集合
        redisService.add2Set(key, message.getRecipient());
        logger.info("message {} 发送及保存时间 {}", chatMessage, l);
        mongoTemplate.save(chatMessage);
    }

    /**
     *
     * @param friendId 好友的userId
     * @param offset 分页查询的偏移量
     * @param size 分页查询的页面大小
     * @description 查找与用户的聊天记录
     * @return 返回一个装载MessageVO对象的列表
     */
    @Override
    public List<MessageVO> findChatRecord(String friendId, int offset, int size) {
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
        List<MessageVO> messageVOS = convertListToMessageVO(chatMessages);
        logger.info("查询结果 {}", chatMessages);
        long count = mongoTemplate.count(query, ChatMessage.class);

        //todo 异步将本页所有的消息设置为已读状态

        return messageVOS;
    }

    /**
     * @description 查找消息列表
     * @return 返回一个装载着简单消息项MessageListItemVO的列表
     */
    @Override
    public List<MessageListItemVO> findMessageList() {
        //获取用户id以redis中用户的消息列表
        String userId = TokenHolder.get();
        String key = USER_MESSAGE_LIST_KEY + userId;
        Set<String> set = redisService.getCacheSet(key);
        List<SimpleUser> users = remoteUserService.selectSimpleUsers(set);
        List<MessageListItemVO> messageListVOS = new ArrayList<>();
        for (SimpleUser friend : users) {
            //设置查询条件为发送者或接受者为双方id
            Criteria criteria = new Criteria().orOperator(
                    Criteria.where("sender").is(userId).and("receiver").is(friend.getUserId()),
                    Criteria.where("sender").is(friend.getUserId()).and("receiver").is(userId)
            );
            Query query = new Query(criteria);
            query.with(Sort.by(Sort.Direction.DESC, "messageTime"));
            query.limit(1);

            List<ChatMessage> messages = mongoTemplate.find(query, ChatMessage.class);

            //获取与某一好友的未读消息数量
            int count = countUnreadMessages(userId, friend.getUserId());
            //设置消息列表项的信息
            MessageListItemVO messageListItemVO = new MessageListItemVO();
            messageListItemVO.setUserId(friend.getUserId());
            messageListItemVO.setUsername(friend.getUsername());
            messageListItemVO.setAvatar(minIOService.path2Link(friend.getAvatarUrl()));
            messageListItemVO.setUnReadMessageNumber(count);

            //获取与好友最新的一条聊天记录
            //没有聊天记录则创建未初始化的消息对象，有聊天记录则获取最新的一条消息
            ChatMessage lastMessage = new ChatMessage();
            if (!messages.isEmpty()) {
                lastMessage = messages.getFirst();
            }

            messageListItemVO.setMessageType(lastMessage.getType());
            messageListItemVO.setLastMessageContent(lastMessage.getContent());

            messageListItemVO.setLastMessageTime(new Date(lastMessage.getMessageTime()));

            //将消息列表项加入列表中
            messageListVOS.add(messageListItemVO);
        }
        return messageListVOS;
    }

    /**
     *
     * @param userId 自身userId
     * @param friendId 好友的userId
     * @description 计算与用户聊天中的未读消息的数量
     * @return 返回未读消息的数量值
     */
    public int countUnreadMessages(String userId, String friendId) {
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("sender").is(friendId).and("receiver").is(userId),
                Criteria.where("status").is("NOT_READ")
        );
        Query query = new Query(criteria);
        return (int) mongoTemplate.count(query, ChatMessage.class);
    }

    /**
     *
     * @param chatMessages 实体ChatMessage的列表
     * @description 将ChatMessage列表转换成MessageVO列表
     * @return 返回将ChatMessage列表中的每一项转换成MessageVO后的列表
     */
    private List<MessageVO> convertListToMessageVO(List<ChatMessage> chatMessages) {
        List<MessageVO> messageVOS = new ArrayList<>();
        for (ChatMessage chatMessage : chatMessages) {
            MessageVO messageVO = new MessageVO();
            messageVO.setContent(chatMessage.getContent());
            messageVO.setSenderId(chatMessage.getSender());
            messageVO.setType(chatMessage.getType());
            messageVO.setMessageTime(chatMessage.getMessageTime());
            String senderId = chatMessage.getSender();
            Map<String, String > userbaseInfo = redisService.getCacheMap(USER_BASE_INFO_KEY + senderId);
            if (userbaseInfo.isEmpty()) {
                userbaseInfo = remoteUserService.getUserBaseInfoByUserId(senderId);
                redisService.setCacheMap(USER_BASE_INFO_KEY + senderId, userbaseInfo);
            }
            messageVO.setAvatarUrl(minIOService.path2Link(userbaseInfo.get("avatarUrl")));
            messageVO.setSenderName(userbaseInfo.get("username"));
            messageVOS.add(messageVO);
        }
        return messageVOS;
    }

    /**
     * @description 静态内部类，用于异步执行将某一页聊天记录标记为已读状态
     */
    private class HandleMessageTask implements Runnable {

        private final List<ChatMessage> chatMessages;

        public HandleMessageTask(List<ChatMessage> chatMessages) {
            this.chatMessages = chatMessages;
        }

        @Override
        public void run() {
            List<String> messageIds = chatMessages.stream().map(ChatMessage::getId).toList();
            Query query = new Query(Criteria.where("_id").in(messageIds));
            Update update = new Update().set("status", MessageStatus.READED);
            mongoTemplate.updateMulti(query, update, ChatMessage.class);
        }
    }

}
