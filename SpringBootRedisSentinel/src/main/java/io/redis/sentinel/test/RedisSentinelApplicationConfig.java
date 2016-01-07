/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.redis.sentinel.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StopWatch;

@Configuration
@EnableAutoConfiguration
public class RedisSentinelApplicationConfig {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = SpringApplication.run(RedisSentinelApplicationConfig.class, args);

		StringRedisTemplate template = context.getBean(StringRedisTemplate.class);
		template.afterPropertiesSet();
		template.opsForValue().set("loop-forever", "0");

		StopWatch stopWatch = new StopWatch();

		while (true) {

			try {
				String value = "IT:= " + template.opsForValue().increment("loop-forever", 1);
				printBackFromErrorStateInfoIfStopWatchIsRunning(stopWatch);
				System.out.println(value);
			} catch (RuntimeException e) {
				System.err.println(e.getCause().getMessage());
				startStopWatchIfNotRunning(stopWatch);
			}

			Thread.sleep(1000);
		}
	}

	private static void startStopWatchIfNotRunning(StopWatch stopWatch) {
		if (!stopWatch.isRunning()) {
			stopWatch.start();
		}
	}

	private static void printBackFromErrorStateInfoIfStopWatchIsRunning(
			StopWatch stopWatch) {

		if (stopWatch.isRunning()) {
			stopWatch.stop();
			System.err.println("INFO: Recovered after: "
					+ stopWatch.getLastTaskInfo().getTimeSeconds());
		}
	}
}
