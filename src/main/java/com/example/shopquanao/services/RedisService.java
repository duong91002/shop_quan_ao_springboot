package com.example.shopquanao.services;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

	private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void saveToken(String key, String token, long expirationMinutes) {
        redisTemplate.opsForValue().set("verify:" + key, token, Duration.ofMinutes(expirationMinutes));
    }

    public String getToken(String key) {
        return redisTemplate.opsForValue().get("verify:" + key);
    }

    public void deleteToken(String key) {
        redisTemplate.delete("verify:" + key);
    }
}
