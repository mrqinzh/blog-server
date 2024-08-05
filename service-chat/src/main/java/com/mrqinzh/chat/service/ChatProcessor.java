//package com.mrqinzh.chat.service;
//
//import com.mrqinzh.common.enums.chat.ChatType;
//import com.unfbx.chatgpt.entity.chat.ChatCompletion;
//import okhttp3.sse.EventSourceListener;
//
//public interface ChatProcessor {
//
//    long chat(String question);
//
//    boolean support(ChatType chatType);
//
//    /**
//     * todo 拿掉下面两个方法，因为只属于流式返回
//     */
//    void streamCompletions(String question, EventSourceListener eventSourceListener);
//    void streamChatCompletion(ChatCompletion completion, EventSourceListener eventSourceListener);
//}
