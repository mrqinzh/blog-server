//package com.mrqinzh.common.vo.chat;
//
//import com.unfbx.chatgpt.entity.chat.ChatChoice;
//import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
//import com.unfbx.chatgpt.entity.common.Usage;
//
//import java.util.List;
//import java.util.UUID;
//
//public class ChatConversationVO {
//
//    private String text;
//    private String conversationId;
//    private String id;
//
//    private Detail detail;
//
//    public static ChatConversationVO toChatConversationVO(ChatCompletionResponse response) {
//        ChatConversationVO vo = new ChatConversationVO();
//        // todo
//        vo.setText(response.getChoices().get(0).getDelta().getContent());
//        vo.setConversationId(UUID.randomUUID().toString());
//
//        vo.setId(response.getId());
//
//        Detail detail = new Detail();
//        detail.setChoices(response.getChoices());
//        detail.setCreated(response.getCreated());
//        detail.setId(response.getId());
//        detail.setModel(response.getModel());
//        detail.setUsage(response.getUsage());
//        vo.setDetail(detail);
//        return vo;
//    }
//
//    public static class Detail {
//        private List<ChatChoice> choices;
//        private long created;
//        private String id;
//        private String model;
//        private String object;
//        private Usage usage;
//
//        public List<ChatChoice> getChoices() {
//            return choices;
//        }
//
//        public void setChoices(List<ChatChoice> choices) {
//            this.choices = choices;
//        }
//
//        public long getCreated() {
//            return created;
//        }
//
//        public void setCreated(long created) {
//            this.created = created;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getModel() {
//            return model;
//        }
//
//        public void setModel(String model) {
//            this.model = model;
//        }
//
//        public String getObject() {
//            return object;
//        }
//
//        public void setObject(String object) {
//            this.object = object;
//        }
//
//        public Usage getUsage() {
//            return usage;
//        }
//
//        public void setUsage(Usage usage) {
//            this.usage = usage;
//        }
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String getConversationId() {
//        return conversationId;
//    }
//
//    public void setConversationId(String conversationId) {
//        this.conversationId = conversationId;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public Detail getDetail() {
//        return detail;
//    }
//
//    public void setDetail(Detail detail) {
//        this.detail = detail;
//    }
//}
