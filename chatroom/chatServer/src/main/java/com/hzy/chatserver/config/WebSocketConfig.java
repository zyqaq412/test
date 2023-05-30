package com.hzy.chatserver.config;

import com.hzy.chatserver.server.ChatHandshakeInterceptor;
import com.hzy.chatserver.server.ChatWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @title: WebSocketConfig
 * @Author zxwyhzy
 * @Date: 2023/5/7 20:05
 * @Version 1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ChatHandshakeInterceptor chatHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatWebSocketHandler(), "/chatroom").setAllowedOrigins("*")
                .addInterceptors(chatHandshakeInterceptor);
    }
}

