package com.mrqinzh.chat.adapter;

import com.mrqinzh.chat.listener.OpenAIWebSocketEventSourceListener;
import com.mrqinzh.chat.service.ChatManageService;
import com.mrqinzh.common.enums.chat.ChatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class WebSocketChatAdapter {

    @Autowired
    private ChatManageService chatManageService;

    public void streamChat(String prompt, Session session) {
        chatManageService.getChatProcessor(ChatType.STREAM).streamCompletions(prompt, new OpenAIWebSocketEventSourceListener(session));
    }

}
