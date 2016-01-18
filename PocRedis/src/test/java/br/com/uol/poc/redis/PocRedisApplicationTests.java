package br.com.uol.poc.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PocRedisApplication.class)
@WebAppConfiguration
public class PocRedisApplicationTests {

	@Test
	public void contextLoads() {
	}

}
