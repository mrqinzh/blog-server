//package com.mrqinzh.chat.service;
//
//import com.google.common.collect.Lists;
//import com.mrqinzh.common.enums.chat.ChatType;
//import com.unfbx.chatgpt.OpenAiStreamClient;
//import com.unfbx.chatgpt.entity.chat.ChatCompletion;
//import com.unfbx.chatgpt.entity.chat.Message;
//import com.unfbx.chatgpt.sse.ConsoleEventSourceListener;
//import okhttp3.sse.EventSourceListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class StreamChatProcessor implements ChatProcessor {
//
//    @Autowired
//    private OpenAiStreamClient openAiStreamClient;
//
//    @Override
//    public long chat(String question) {
//
//        Message currentMessage = Message.builder().content(question).role(Message.Role.USER).build();
//
//        ChatCompletion completion = ChatCompletion
//                .builder()
//                .messages(Lists.newArrayList(currentMessage))
//                .model(ChatCompletion.Model.GPT_3_5_TURBO_0301.getName())
//                .build();
//        this.streamChat(completion);
//
//        return completion.tokens();
//    }
//
//    private ChatCompletion promptWrapper2ChatCompletion(String prompt) {
//        return ChatCompletion
//                .builder()
//                .messages(Lists.newArrayList(Message.builder().content(prompt).role(Message.Role.USER).build()))
//                .model(ChatCompletion.Model.GPT_3_5_TURBO_0301.getName())
//                .build();
//    }
//
//    private void streamChat(ChatCompletion completion) {
//        openAiStreamClient.streamChatCompletion(completion, new ConsoleEventSourceListener());
//    }
//
//    private void streamChat(String question) {
//        openAiStreamClient.streamCompletions(question, new ConsoleEventSourceListener());
//    }
//
//    public void streamCompletions(String question, EventSourceListener eventSourceListener) {
//
//        this.streamChatCompletion(this.promptWrapper2ChatCompletion(question), eventSourceListener);
//    }
//
//    public void streamChatCompletion(ChatCompletion completion, EventSourceListener eventSourceListener) {
//        openAiStreamClient.streamChatCompletion(completion, eventSourceListener);
//    }
//
//    @Override
//    public boolean support(ChatType chatType) {
//        return chatType == ChatType.STREAM;
//    }
//}
