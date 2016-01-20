package br.com.uol.poc.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableTransactionManagement
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class PocRedisApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PocRedisApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(this.getClass());
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		redisTemplate.setKeySerializer(new GenericToStringSerializer<>(Object.class));
		redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
		redisTemplate.afterPropertiesSet();

		RedisCacheManager cache = new RedisCacheManager(redisTemplate);
		cache.setDefaultExpiration(90);

		Map<String, Long> expires = Collections.unmodifiableMap(Stream.of(
				new SimpleEntry<>("books", 60L),
				new SimpleEntry<>("user", 30L)
		).collect(
			Collectors.toMap(e -> e.getKey(),e -> e.getValue())
		));

		cache.setExpires(expires);

		return cache;
	}

}
