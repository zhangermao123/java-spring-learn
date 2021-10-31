package com.zw;

import com.zw.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoRedisApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, Serializable> redisCacheTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSet() {
		log.info("testSet set begin...");
		redisCacheTemplate.opsForValue().set("zw-get-user",new User(2l,"getUserzw"));
		log.info("testSet set end...");
	}

	@Test
	public void testGet() {
		User user = (User) redisCacheTemplate.opsForValue().get("zw-get-user");
		log.info("testSet get end ..." + user);
	}

}
