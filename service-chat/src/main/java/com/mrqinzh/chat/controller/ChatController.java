package com.mrqinzh.chat.controller;

import com.mrqinzh.chat.service.ChatManageService;
import com.mrqinzh.common.request.chat.ChatConversationRequest;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.chat.ChatResponse;
import com.unfbx.chatgpt.OpenAiStreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
@RequestMapping("chat")
public class ChatController {

    @Autowired
    private ChatManageService chatManageService;
    @Autowired
    private OpenAiStreamClient openAiStreamClient;

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public Resp session() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("auth", false);
        result.put("model", "ChatGPTAPI");
        return DataResp.ok(result);
    }

//    @RequestMapping(value = "/session", method = RequestMethod.POST)
//    public Resp session() {
//        Model model = openAiClient.model("gpt-3.5-turbo");
//        return DataResp.ok(model);
//    }

//    @RequestMapping(value = "/chat-process", method = RequestMethod.POST)
//    public Resp testOpenAiClient(@RequestBody ChatConversationRequest request) throws JsonProcessingException {
//        Message message = Message.builder()
//                .role(Message.Role.USER)
//                .content(request.getPrompt())
//                .build();
//        ChatCompletion chatCompletion = ChatCompletion
//                .builder()
//                .messages(Arrays.asList(message))
//                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
//                .build();
//        String respText = "{\"id\":\"chatcmpl-7U6wWiDT14MklvCsJe0gYc5we148r\",\"object\":\"chat.completion\",\"created\":1687410884,\"model\":\"gpt-3.5-turbo-0301\",\"choices\":[{\"index\":0,\"message\":{\"role\":\"assistant\",\"content\":\"你好，有什么我可以帮助你的吗？\"},\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":18,\"completion_tokens\":18,\"total_tokens\":36}}";
////        ChatCompletionResponse chatCompletionResponse = objectMapper.readValue(respText, ChatCompletionResponse.class);
//        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
//
//        chatCompletionResponse.getChoices().forEach(e -> {
//            System.out.println(e.getMessage());
//        });
//
//        ChatConversationVO data = ChatConversationVO.toChatConversationVO(chatCompletionResponse);
//
//        return DataResp.ok(data);
//    }

//    @RequestMapping(value = "/chat-process", method = RequestMethod.POST)
//    public void testOpenAiClient(@RequestBody ChatConversationRequest request) throws JsonProcessingException {
//        Message message = Message.builder()
//                .role(Message.Role.USER)
//                .content(request.getPrompt())
//                .build();
//        ChatCompletion chatCompletion = ChatCompletion
//                .builder()
//                .messages(Arrays.asList(message))
//                .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
//                .build();
//        String respText = "{\"id\":\"chatcmpl-7U6wWiDT14MklvCsJe0gYc5we148r\",\"object\":\"chat.completion\",\"created\":1687410884,\"model\":\"gpt-3.5-turbo-0301\",\"choices\":[{\"index\":0,\"message\":{\"role\":\"assistant\",\"content\":\"你好，有什么我可以帮助你的吗？\"},\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":18,\"completion_tokens\":18,\"total_tokens\":36}}";
////        ChatCompletionResponse chatCompletionResponse = objectMapper.readValue(respText, ChatCompletionResponse.class);
//        chatManageService.streamChat(request.getPrompt());
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }

    @PostMapping("/chat-process")
    public ChatResponse sseChat(@RequestBody ChatConversationRequest request, HttpServletResponse res) {

        long tokens = chatManageService.streamChat(request.getPrompt());
        ChatResponse response = new ChatResponse();
        response.setQuestionTokens(tokens);
        return response;
    }

}
