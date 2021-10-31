package com.zw;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoMongodbApplication.class, args);
	}

	@Bean
	public Snowflake snowflake() {
		return IdUtil.createSnowflake(1, 1);
	}
}
