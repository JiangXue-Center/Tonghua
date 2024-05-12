package com.hf.communication.service;

import com.hf.communication.enums.MessageType;
import com.hf.communication.model.dto.Message;
import com.hf.communication.model.entity.ChatMessage;
import com.hf.communication.model.vo.MessageListItemVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChatService {

//    void saveOfflineMessage(Message message, MessageType type);

    void saveMessage(Message message, String type);

    Page<ChatMessage> findPrivateMessages(String userId, int offset, int size);

    List<MessageListItemVO> findMessageList();
}
