package io.redis.cluster.test;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
@EnableAutoConfiguration
public class RedisClusterApplicationConfig {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RedisClusterApplicationConfig.class, args);
		
		
		final Set<HostAndPort> jedisClusterNode=new HashSet<HostAndPort>();
		  jedisClusterNode.add(new HostAndPort("172.17.0.2",6379));
		  jedisClusterNode.add(new HostAndPort("172.17.0.3",6379));
		  jedisClusterNode.add(new HostAndPort("172.17.0.4",6379));
		  jedisClusterNode.add(new HostAndPort("172.17.0.5",6379));
		  jedisClusterNode.add(new HostAndPort("172.17.0.6",6379));
		  jedisClusterNode.add(new HostAndPort("172.17.0.7",6379));
		  
		  final JedisCluster jc= new JedisCluster(jedisClusterNode);
		  
		  int i = 0;
		  while(true){
		      String key = "key"+i;
              jc.set(key, "value"+i);
		      System.out.println(key+" Registrada!");
		      i++;
		      Thread.sleep(2000);
		  }
		
	}
}
