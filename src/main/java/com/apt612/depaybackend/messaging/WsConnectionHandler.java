package com.apt612.depaybackend.messaging;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class WsConnectionHandler implements ApplicationListener<SessionConnectEvent> {

    private ConcurrentMap<String, String> userSessions = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(SessionConnectEvent sessionConnect) {

        Map header=sessionConnect.getMessage().getHeaders();
        String userId=StompHeaderAccessor.getFirstNativeHeader("user",header);



        System.out.println(userId);
    }
}
