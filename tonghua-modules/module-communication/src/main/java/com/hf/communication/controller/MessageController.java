package com.hf.communication.controller;

import com.hf.communication.model.entity.ChatMessage;
import com.hf.communication.model.vo.MessageListItemVO;
import com.hf.communication.service.ChatService;
import com.hf.core.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/user/{friendId}/offset/{offset}/size/{size}")
    public Result<Page<ChatMessage>> selectPrivateMessage(
            @PathVariable("friendId") String friendId,
            @PathVariable("offset") Integer offset,
            @PathVariable("size") Integer size) {
        Page<ChatMessage> privateMessages = chatService.findPrivateMessages(friendId, offset, size);
        return Result.success(privateMessages);
    }

    @GetMapping("/list")
    public Result<List<MessageListItemVO>> selectMessageList() {
        List<MessageListItemVO> messageList = chatService.findMessageList();
        return Result.success(messageList);
    }


}
