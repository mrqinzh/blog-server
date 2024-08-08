package com.mrqinzh.framework.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class GlobalAppConfiguration {

    public static final String YYYY_MM_DD = "yyyyMMdd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper mapper = builder.createXmlMapper(false).build();
        // 忽略值为null的属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);

        SimpleModule module = new SimpleModule();
        module.addSerializer(Long.class, new ToStringSerializer());
        module.addSerializer(Date.class, new DateSerializer(false, new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS)));
        module.addDeserializer(Date.class, new DateDeserializers.DateDeserializer(new DateDeserializers.DateDeserializer(),
                new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS), YYYY_MM_DD_HH_MM_SS));
        mapper.registerModule(module);
        return mapper;
    }

}
