package com.zw.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zw.handler.CustomBlockHandler;
import com.zw.handler.MyFallbackHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-07-07
 */
@RestController
public class LimitController {

    @GetMapping("/testLimit")
    @SentinelResource(value = "testLimit",blockHandlerClass = CustomBlockHandler.class,blockHandler = "handlerException",fallbackClass = MyFallbackHandler.class,fallback = "getFallback")
    public String testLimit(String s){
        if("fallback".equals(s)){
            throw new RuntimeException();
        }
       return "limit test : "+  System.currentTimeMillis();
    }

    @GetMapping("/updateLimit")
    public String testUpdate(){
        return "limit update :" + System.currentTimeMillis();
    }

}
