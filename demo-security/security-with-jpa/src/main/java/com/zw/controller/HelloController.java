package com.zw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className HelloController
 * @description TODD
 * @author zhangwei
 * @date 2021/6/9
 * @version 1.0
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/hello1")
    public String hello1() {
        return "hello1";
    }
    @RequestMapping("/index")
    public String hello2() {
        return "跳转到index";
    }

    @RequestMapping("/f1")
    public String f1() {
        return "密码失败";
    }
    @RequestMapping("/f2")
    public String f2() {
        return "密码错误";
    }


    @GetMapping("/admin/hello")
    public String admin() {
        return "admin权限";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "user和admin权限显示";
    }
}
