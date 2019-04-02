package com.bckj.nacosdemo.accountservice.config;


import com.bckj.nacosdemo.commons.redis.ObjectKeyGenerator;
import com.bckj.nacosdemo.commons.redis.RedisAssistant;
import com.bckj.nacosdemo.commons.redis.RedisCache;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.NullValue;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * redis配置
 * @author wangfengchen
 *
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * 默认缓存
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory,
                                                       GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(genericJackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        objectMapper.registerModule(new SimpleModule().addSerializer(new StdSerializer<NullValue>(NullValue.class) {

            @Override
            public void serialize(NullValue nullValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("@class", NullValue.class.getName());
                jsonGenerator.writeEndObject();
            }
        }));
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    @Bean
    ObjectKeyGenerator objectKeyGenerator() {
        return new ObjectKeyGenerator();
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate,
                                     GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer) {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        RedisCache defCache = new RedisCache("default", redisTemplate, genericJackson2JsonRedisSerializer);
        caches.add(defCache);
        cacheManager.setCaches(caches);
        return cacheManager;
    }

    @Bean
    RedisAssistant redisAssistant(RedisTemplate<String, Object> redisTemplate) {
        return new RedisAssistant(redisTemplate);
    }
}
