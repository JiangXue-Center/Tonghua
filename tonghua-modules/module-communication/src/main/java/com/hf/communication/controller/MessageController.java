package com.hf.communication.controller;

import com.hf.communication.model.entity.ChatMessage;
import com.hf.communication.model.vo.MessageListItemVO;
import com.hf.communication.model.vo.MessageVO;
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

    /**
     * @description 查询与用户聊天记录的接口
     */
    @GetMapping("/user/{friendId}/offset/{offset}/size/{size}")
    public Result<List<MessageVO>> selectPrivateMessage(
            @PathVariable("friendId") String friendId,
            @PathVariable("offset") Integer offset,
            @PathVariable("size") Integer size) {
        List<MessageVO> privateMessages = chatService.findChatRecord(friendId, offset, size);
        return Result.success(privateMessages);
    }

    /**
     * @description 查询消息列表的接口
     */
    @GetMapping("/list")
    public Result<List<MessageListItemVO>> selectMessageList() {
        List<MessageListItemVO> messageList = chatService.findMessageList();
        return Result.success(messageList);
    }


}