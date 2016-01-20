package br.com.redis.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@EnableCaching
@SpringBootApplication
public class RedisClusterApplicationConfig extends CachingConfigurerSupport {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(RedisClusterApplicationConfig.class, args);
  }

  @Bean
  public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
    redisTemplate.setKeySerializer(new GenericToStringSerializer<>(Object.class));
    redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
    // Number of seconds before expiration. Defaults to unlimited (0)
    cacheManager.setDefaultExpiration(300);
    return cacheManager;
  }

  @Override
  public KeyGenerator keyGenerator() {
    return (o, method, params) -> {
      StringBuilder sb = new StringBuilder();
      sb.append(o.getClass().getName());
      sb.append(":"+method.getName()+"(");
    for (Object param : params) {
        sb.append(param.getClass().getSimpleName()+":"+param.toString());
  }
    sb.append(")");
    return sb.toString();
  };
  }
}
