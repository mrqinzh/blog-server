package com.mrqinzh.chat.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrqinzh.common.vo.chat.ChatConversationVO;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class OpenAIWebSocketEventSourceListener extends EventSourceListener {

    public static final String DONE = "[DONE]";

    private Session session;

    public OpenAIWebSocketEventSourceListener(Session session) {
        this.session = session;
    }

    private static ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onOpen(EventSource eventSource, Response response) {
        log.info("OpenAI建立sse连接...");
    }

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        log.info("OpenAI返回数据：{}", data);
        if (data.equals(DONE)) {
            this.sendDoneMsg();

            return;
        }
        ChatCompletionResponse completionResponse = getObjectMapper().readValue(data, ChatCompletionResponse.class); // 读取Json
        this.sendOpenAiSocketMsg(completionResponse);
    }

    private void sendOpenAiSocketMsg(ChatCompletionResponse chatCompletionResponse) throws IOException {

        ChatConversationVO data = ChatConversationVO.toChatConversationVO(chatCompletionResponse);

        String respText = getObjectMapper().writeValueAsString(data);

        session.getBasicRemote().sendText(respText);
    }

    private void sendDoneMsg() throws IOException {
        log.info("OpenAI返回数据结束了");
        session.getBasicRemote().sendText("[DONE]");
    }


    @Override
    public void onClosed(EventSource eventSource) {
        log.info("OpenAI关闭sse连接...");
    }


    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        if (Objects.isNull(response)) {
            return;
        }
        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            log.error("OpenAI  sse连接异常data：{}，异常：{}", body.string(), t);
        } else {
            log.error("OpenAI  sse连接异常data：{}，异常：{}", response, t);
        }
        eventSource.cancel();
    }
}
