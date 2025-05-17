package com.jeesite.modules.dataservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // 推送路径前缀
        registry.setApplicationDestinationPrefixes("/app"); // 客户端发送消息前缀
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 客户端连接入口
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }
}