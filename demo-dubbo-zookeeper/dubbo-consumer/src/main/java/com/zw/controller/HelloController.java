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
 * @date 2021-06-18
 */
@RestController
@Slf4j
public class HelloController {
    //dubbo注解
    @Reference
    HelloService helloService;

    @GetMapping("/zookeeper/hello")
    public String sayHello(@RequestParam String name){
        log.info("[dubbo-consumer] the caller one is "+ name);
        return helloService.sayHello(name);
    }
}
