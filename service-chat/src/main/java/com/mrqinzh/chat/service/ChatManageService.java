package com.mrqinzh.chat.service;

import com.mrqinzh.common.enums.chat.ChatType;

public interface ChatManageService {


    long streamChat(String question);

    ChatProcessor getChatProcessor(ChatType chatType);
}
