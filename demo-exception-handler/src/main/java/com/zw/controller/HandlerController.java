package com.zw.controller;

import com.zw.constant.Status;
import com.zw.exception.ParamException;
import com.zw.pojo.result.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-05-26
 */
@RestController
public class HandlerController {

    @GetMapping("/test")
    public User getTest(@RequestParam(required = true) String name){
        //参数错误
        if("zw".equals(name)){
            throw  new ParamException();
        }

        return new User("Hello ,"+ name);
    }


    @GetMapping("/test2")
    public String getTest(){

        return "测试一下 :";
    }
}
