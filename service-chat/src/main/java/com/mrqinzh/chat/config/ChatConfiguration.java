//package com.mrqinzh.chat.config;
//
//import com.google.common.collect.Lists;
//import com.mrqinzh.common.constant.MyConstant;
//import com.unfbx.chatgpt.OpenAiClient;
//import com.unfbx.chatgpt.OpenAiStreamClient;
//import com.unfbx.chatgpt.function.KeyRandomStrategy;
//import com.unfbx.chatgpt.interceptor.OpenAILogger;
//import com.unfbx.chatgpt.interceptor.OpenAiResponseInterceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetSocketAddress;
//import java.net.Proxy;
//import java.time.Duration;
//import java.util.Arrays;
//import java.util.concurrent.TimeUnit;
//
//import static com.theokanning.openai.service.OpenAiService.defaultClient;
//
//@Configuration
//public class ChatConfiguration {
//
//    @Bean
//    public Proxy proxy() {
//        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(MyConstant.LOCALHOST, Integer.valueOf(MyConstant.CLASH_PORT)));
//    }
//
//    @Bean
//    public OkHttpClient okHttpClient(Proxy proxy) {
//        return defaultClient(MyConstant.CHAT_GPT_API_KEY, Duration.ofMinutes(3))
//                .newBuilder()
//                .proxy(proxy)
//                .build();
//    }
//
//    @Bean
//    public OpenAiClient openAiClient(OkHttpClient okHttpClient) {
//        return new OpenAiClient.Builder()
//                .apiKey(Lists.newArrayList(MyConstant.CHAT_GPT_API_KEY))
//                //支持多key传入，请求时候随机选择
//                //自定义key的获取策略：默认KeyRandomStrategy
//                //.keyStrategy(new KeyRandomStrategy())
//                .keyStrategy(new KeyRandomStrategy())
//                .okHttpClient(okHttpClient)
//                //自己做了代理就传代理地址，没有可不不传,(关注公众号回复：openai ，获取免费的测试代理地址)
////                .apiHost("https://自己代理的服务器地址/")
//                .build();
//    }
//
//    @Bean
//    public OpenAiStreamClient openAiStreamClient(OkHttpClient okHttpClient) {
//        return OpenAiStreamClient.builder()
//                .apiKey(Arrays.asList(MyConstant.CHAT_GPT_API_KEY))
//                //自定义key的获取策略：默认KeyRandomStrategy
////                .keyStrategy(new KeyRandomStrategy())
//                .keyStrategy(new KeyRandomStrategy())
//                .okHttpClient(okHttpClient)
//                //自己做了代理就传代理地址，没有可不不传（(关注公众号回复：openai ，获取免费的测试代理地址)）
////                .apiHost("https://自己代理的服务器地址/")
//                .build();
//    }
//
//}
