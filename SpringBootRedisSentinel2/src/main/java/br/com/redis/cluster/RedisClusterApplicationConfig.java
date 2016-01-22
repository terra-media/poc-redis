package br.com.redis.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@EnableCaching
@SpringBootApplication
public class RedisClusterApplicationConfig extends CachingConfigurerSupport {
  
  private final RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();

  public static void main(String[] args) throws Exception {
    SpringApplication.run(RedisClusterApplicationConfig.class, args);
  }

  @Bean
  public RedisTemplate<?, ?> sessionRedisTemplate(RedisConnectionFactory connectionFactory) {
    final GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
    final GenericToStringSerializer<Object> genericToStringSerializer = new GenericToStringSerializer(Object.class);
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setKeySerializer(genericToStringSerializer);
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setHashKeySerializer(genericToStringSerializer);
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    return redisTemplate;
  }
  
  @Bean
  public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    cacheManager.setDefaultExpiration(300);
    return cacheManager;
  }

}
