package br.com.uol.poc.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by vrx_hora on 06/01/16.
 */
@Component
public class RedisRepositoryCommunication {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public  RedisRepositoryCommunication (StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

