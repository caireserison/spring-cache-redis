package com.example.spring.cache.redis.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {
    private static final String KEY = "test-key";

    private final HashOperations<String, String, Object> hashOperations;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.cache.redis.expire-minute}")
    private Long expireRedis;

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public Object getObject(String id) {
        return hashOperations.get(KEY, id);
    }

    public void saveObject(String id, Object obj) {
        hashOperations.put(KEY, id, obj);
        redisTemplate.expire(KEY, expireRedis, TimeUnit.MINUTES);
    }

    public void deleteObject(String id) {
        hashOperations.delete(KEY, id);
    }
}
