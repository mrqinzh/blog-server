package com.mrqinzh.common.vo.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatResponse {
    /**
     * 问题消耗tokens
     */
    @JsonProperty("question_tokens")
    private long questionTokens = 0;

    public long getQuestionTokens() {
        return questionTokens;
    }

    public void setQuestionTokens(long questionTokens) {
        this.questionTokens = questionTokens;
    }
}