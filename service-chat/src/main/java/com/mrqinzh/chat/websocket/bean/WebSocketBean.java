package com.mrqinzh.chat.websocket.bean;

import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;

public class WebSocketBean {
    // websocket
    private WebSocketSession webSocketSession;
    // 客户端id
    private String clientId;
    // 最后更新时间
    private LocalDateTime lastMessageTime;

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public void setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(LocalDateTime lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }
}
