package com.zw;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableDubboConfiguration
@SpringBootApplication
public class DubboProviderRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DubboProviderRedisApplication.class, args);
	}

}
