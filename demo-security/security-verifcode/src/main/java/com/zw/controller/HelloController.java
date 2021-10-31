package com.zw.controller;

import com.zw.service.AuthenticationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-11
 */
@RestController
public class HelloController {
    @Autowired
    AuthenticationDetailService service;

    @GetMapping("/hello")
    public String hello() {
        service.getDetail();
        return "hello";
    }

    //为了匹配login的get,post 两个方法需要使用这种方式，防止illgerror
    @RequestMapping("/index")
    public String index() {
        return "登录成功";
    }

    @RequestMapping("/toerror")
    public String error() {
        return "登录失败";
    }
}
