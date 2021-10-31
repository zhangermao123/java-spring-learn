package com.zw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-05-21
 */
@RestController
public class AdminClientController {
    @GetMapping("/client")
    public String getAdminClient(){
        return "This is a Spring Boot Admin Client.";
    }
}
