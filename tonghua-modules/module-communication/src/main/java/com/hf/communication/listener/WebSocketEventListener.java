package com.hf.communication.listener;

import cn.hutool.core.util.StrUtil;
import com.hf.cache.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;

import static com.hf.cache.constants.RedisConstant.*;

@Component
public class WebSocketEventListener {

    @Autowired
    private RedisService redisService;

    private final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        logger.info("sockjs连接时整体消息 {}", event.getMessage());
        String sessionId = accessor.getSessionId();
        String userId = accessor.getFirstNativeHeader("userId");
        if (!StrUtil.isBlank(userId)) {
            //userId: sessionId
            String key = ONLINE_USER_KEY + sessionId;
            redisService.setCacheObject(key, userId);
            //sessionId
            redisService.add2Set(ONLINE_USER_SET_KEY, userId);
        }

    }

    @EventListener
    public void handleWebSocketDisConnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        logger.info("sockjs断开连接时整体消息 {}", event.getMessage());
        String key = ONLINE_USER_KEY + sessionId;
        String userId = redisService.getCacheObject(key);
        logger.info("sockjs断开连接时用户Id {}", userId);
        redisService.removeFromSet(ONLINE_USER_SET_KEY, userId);
        redisService.deleteObject(key);
    }

}
