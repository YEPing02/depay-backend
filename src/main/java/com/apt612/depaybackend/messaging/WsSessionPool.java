package com.apt612.depaybackend.messaging;

import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WsSessionPool {
    public static ConcurrentMap<String, WebSocketSession> userSessions=new ConcurrentHashMap();
}
