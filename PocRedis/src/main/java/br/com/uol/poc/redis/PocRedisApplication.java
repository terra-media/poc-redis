package br.com.uol.poc.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.atomic.AtomicReference;

@EnableCaching
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
@PropertySource("classpath:/redis.properties")
public class PocRedisApplication extends SpringBootServletInitializer {

	private final Logger log = Logger.getLogger(this.getClass());

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(this.getClass());
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(@Value("${redis.host}") String redisHostName,
														 @Value("${redis.port}") int redisPort) {
		AtomicReference<JedisConnectionFactory> factory = new AtomicReference<>();
        factory.set(new JedisConnectionFactory());
        factory.get().setHostName(redisHostName);
		factory.get().setPort(redisPort);
		factory.get().setUsePool(true);
		return factory.get();
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}

	public static void main(String[] args) {
		SpringApplication.run(PocRedisApplication.class, args);
	}
}
