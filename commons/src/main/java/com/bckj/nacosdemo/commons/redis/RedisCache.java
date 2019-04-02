package com.bckj.nacosdemo.commons.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.Callable;

/**
 * @author wangfengchen
 */
public class RedisCache implements Cache {
    private RedisTemplate<String, Object> redisTemplate;
    private RedisSerializer serializer;
    private String name;
    private long liveTime = 86400;

    public RedisCache(String name, RedisTemplate<String, Object> rt, RedisSerializer rs) {
        this.name = name;
        redisTemplate = rt;
        serializer = rs;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        final String keyf = key.toString();
        Object object = redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] key1 = keyf.getBytes();
            byte[] value = connection.get(key1);
            return toObject(value);
        });
        return object == null ? null : new SimpleValueWrapper(object);
    }

    @Override
    public void put(Object key, Object value) {
        final String keyFnl = key.toString();
        final Object valueFnl = value;
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] keyb = keyFnl.getBytes();
            byte[] valueb = toByteArray(valueFnl);
            connection.set(keyb, valueb);
            connection.expire(keyb, liveTime);
            return 1L;
        });
    }

    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        try {
            bytes = serializer.serialize(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            obj = serializer.deserialize(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void evict(Object key) {
        final String keyf = key.toString();
        redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(keyf.getBytes()));
    }

    @Override
    public void clear() {
        redisTemplate.execute((RedisCallback<String>) connection -> {
            connection.flushDb();
            return "ok";
        });
    }

    @Override
    public <T> T get(Object key, final Class<T> type) {
        ValueWrapper wrapper = this.get(key);
        return wrapper == null ? null : (T) wrapper.get();
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }
}
