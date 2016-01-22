package br.com.uol.poc.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
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
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		final RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
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
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager cache = new RedisCacheManager(redisTemplate);

		//cache.setUsePrefix(true);
		//cache.setCachePrefix(cacheName ->(PocRedisApplication.class.getSimpleName()+":").getBytes());

		cache.setLoadRemoteCachesOnStartup(true);

		cache.setDefaultExpiration(90);
		Map<String, Long> expires = Collections.unmodifiableMap(Stream.of(
			new SimpleEntry<>("books", 60L),
			new SimpleEntry<>("user", 30L)
		).collect(
			Collectors.toMap(SimpleEntry::getKey,SimpleEntry::getValue)
		));
		cache.setExpires(expires);

		return cache;
	}
}
