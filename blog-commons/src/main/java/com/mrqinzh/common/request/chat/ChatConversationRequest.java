package com.mrqinzh.common.request.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ChatConversationRequest implements Serializable {

    /**
     * 输入的文本
     */
    private String prompt;
    private String systemMessage;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;
    private Option options;


    public static class Option implements Serializable {
        private String conversationId;
        private String parentMessageId;

        public String getConversationId() {
            return conversationId;
        }

        public void setConversationId(String conversationId) {
            this.conversationId = conversationId;
        }

        public String getParentMessageId() {
            return parentMessageId;
        }

        public void setParentMessageId(String parentMessageId) {
            this.parentMessageId = parentMessageId;
        }
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTopP() {
        return topP;
    }

    public void setTopP(Double topP) {
        this.topP = topP;
    }

    public Option getOptions() {
        return options;
    }

    public void setOptions(Option options) {
        this.options = options;
    }
}
