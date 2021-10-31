package com.zw.controller;

import com.zw.contant.Status;
import com.zw.entity.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-17
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public ApiResponse test(){
        log.info("============>>>test");
        return ApiResponse.ofSuccess("test2");
    }

    @GetMapping("/errorAuthor")
    public ApiResponse error(){
        return ApiResponse.ofStatus(Status.ACCESS_DENIED);
    }
}
