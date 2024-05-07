package com.pospayment.pospayment.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Redis {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    private StringRedisTemplate redisTemplate;

    public Redis(StringRedisTemplate redisTemplate) {
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
