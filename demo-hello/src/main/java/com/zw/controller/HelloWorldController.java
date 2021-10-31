package com.zw.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-05-20
 */
@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String getHelloWorld(@RequestParam(required = false,name = "who",defaultValue = "zw") String who){
        if (StrUtil.isBlank(who)) {
            who = "World";
        }
        return StrUtil.format("Hello {}!",who);
    }
}
