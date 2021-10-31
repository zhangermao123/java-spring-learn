package com.zw.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.service
 * @descripation TODO
 * @date 2021-06-23
 */
@Slf4j
@Service(interfaceClass = HelloService.class)
@Component
public class RedisServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        log.info("someone is calling me...... [dubbo-provider-redis]");
        return "say hello to "+ name;
    }
}
