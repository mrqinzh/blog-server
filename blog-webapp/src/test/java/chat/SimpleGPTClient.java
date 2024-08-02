package chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.mrqinzh.common.constant.MyConstant;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.completion.CompletionChunk;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Retrofit;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static com.theokanning.openai.service.OpenAiService.defaultClient;
import static com.theokanning.openai.service.OpenAiService.defaultObjectMapper;
import static com.theokanning.openai.service.OpenAiService.defaultRetrofit;

@SpringBootTest
public class SimpleGPTClient {

    private OpenAiService openAiService;

    public OpenAiService getOpenAiService() {
        ObjectMapper mapper = defaultObjectMapper();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(MyConstant.LOCALHOST, Integer.valueOf(MyConstant.CLASH_PORT)));
        OkHttpClient client = defaultClient(MyConstant.CHAT_GPT_API_KEY, Duration.ofSeconds(3))
                .newBuilder()
                .proxy(proxy)
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        OpenAiService service = new OpenAiService(api, client.dispatcher().executorService());
        return service;
    }

    @Test
    public void test() {
        ObjectMapper mapper = defaultObjectMapper();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(MyConstant.LOCALHOST, Integer.valueOf(MyConstant.CLASH_PORT)));
        OkHttpClient client = defaultClient(MyConstant.CHAT_GPT_API_KEY, Duration.ofSeconds(3))
                .newBuilder()
                .proxy(proxy)
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        OpenAiService service = new OpenAiService(api);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("你好")
                .model("ada")
                .echo(true)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
    }

    private List<ChatMessage> formatMessages(String content, String role) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(content);
        chatMessage.setRole(role);
        return Lists.newArrayList(chatMessage);
    }

    @Test
    public void streamTest() {
        OpenAiService openAiService = getOpenAiService();
        CompletionRequest request = CompletionRequest.builder()
                .prompt("你好")
                .stream(true)
                .model("ada")
                .logitBias(new HashMap<>())
                .echo(true)
                .build();
        Flowable<CompletionChunk> flowable = openAiService.streamCompletion(request);
        flowable.subscribe(chunk -> {
            String text = chunk.getChoices().get(0).getText();
            if (text == null) {
                return;
            }
            System.out.print(text);
        });
    }

    public SimpleGPTClient() {
        ObjectMapper mapper = defaultObjectMapper();
        OkHttpClient client = defaultClient(MyConstant.CHAT_GPT_API_KEY, Duration.ofSeconds(10))
                .newBuilder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(MyConstant.LOCALHOST, Integer.parseInt(MyConstant.CLASH_PORT))))
                .build();
        Retrofit retrofit = defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        this.openAiService = new OpenAiService(api, client.dispatcher().executorService());
    }

    @Test
    public void streamChat() {
        SimpleGPTClient simpleGPTClient = new SimpleGPTClient();
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(formatMessages("你好", "user"))
                .stream(true)
//                .n(CHOICES_AMOUNT)
                .model("ada")
                .logitBias(new HashMap<>())
                .temperature(0.8)
                .build();
        openAiService.streamChatCompletion(request).subscribe(chunk -> {
            String text = chunk.getChoices().get(0).getMessage().getContent();
            if (text == null) {
                return;
            }
            System.out.print(text);
        }, Throwable::printStackTrace, openAiService::shutdownExecutor);
    }

}
