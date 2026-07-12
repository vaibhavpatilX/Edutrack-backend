package com.marvellous.MarvellousPortal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // This is the endpoint React will connect to
        registry.addEndpoint("/ws-edutrack")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // Fallback for browsers that don't support raw WebSockets
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // React will listen to routes starting with /topic (e.g., /topic/notices)
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}