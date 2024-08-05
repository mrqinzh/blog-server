//package com.mrqinzh.chat.service;
//
//import com.mrqinzh.common.enums.chat.ChatType;
//import com.mrqinzh.common.exception.BizException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ChatManageServiceImpl implements ChatManageService {
//
//    @Autowired
//    private List<ChatProcessor> processors;
//
//    public long streamChat(String question) {
//        return this.getChatProcessor(ChatType.STREAM).chat(question);
//    }
//
//    public ChatProcessor getChatProcessor(ChatType chatType) {
//        for (ChatProcessor processor : processors) {
//            if (processor.support(chatType)) {
//                return processor;
//            }
//        }
//        throw new BizException("获取GPT处理程序失败");
//    }
//
//}
