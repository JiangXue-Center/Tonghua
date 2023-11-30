package com.hf.communication.dao;

import com.hf.communication.model.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
//    List<ChatMessage> findBySenderIdAndReceiverId(String senderId, String receiverId);
//
}