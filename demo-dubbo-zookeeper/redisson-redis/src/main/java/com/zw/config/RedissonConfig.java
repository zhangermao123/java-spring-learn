package com.zw.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.config
 * @descripation TODO
 * @date 2021-06-23
 */
@Configuration
public class RedissonConfig {
    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public RedissonClient redissonClient() throws IOException {
        Config config = new Config();
        String address = "redis://"+host+":"+port;
        //单机模式
        config.useSingleServer().setAddress(address);
        if(StringUtils.isEmpty(password)){
            config.useSingleServer().setPassword(null);
        }else{
            config.useSingleServer().setPassword(password);
        }
//        哨兵模式
//        Config config = new Config();
//        config.useMasterSlaveServers()
//                //可以用"rediss://"来启用SSL连接
//                .setMasterAddress("redis://***(主服务器IP):6379").setPassword("web2017")
//                .addSlaveAddress("redis://***（从服务器IP）:6379")
//                .setReconnectionTimeout(10000)
//                .setRetryInterval(5000)
//                .setTimeout(10000)
//                .setConnectTimeout(10000);//（连接超时，单位：毫秒 默认值：3000）;
        RedissonClient redisson = Redisson.create(config);
        System.out.println(redisson.getConfig().toJSON().toString());
        return redisson;
    }
}
