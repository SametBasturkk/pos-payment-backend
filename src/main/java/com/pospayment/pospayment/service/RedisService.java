package com.pospayment.pospayment.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisService {

    private StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, String value) {
        log.info("Setting key : {} with value : {}", key, value);
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        log.info("Getting value for key : {}", key);
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        log.info("Deleting key : {}", key);
        redisTemplate.delete(key);
    }
}
