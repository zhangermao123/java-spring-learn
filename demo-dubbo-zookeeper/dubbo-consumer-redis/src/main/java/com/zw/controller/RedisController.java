package com.zw.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zw.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-23
 */
@RestController
@Slf4j
public class RedisController {
    //dubbo注解
    @Reference(interfaceClass = HelloService.class)
    HelloService helloService;

    @GetMapping("/redis/hello")
    public String sayHello(@RequestParam String name){
        log.info("[dubbo-consumer-redis] the caller one is "+ name);
        return helloService.sayHello(name);
    }
}
