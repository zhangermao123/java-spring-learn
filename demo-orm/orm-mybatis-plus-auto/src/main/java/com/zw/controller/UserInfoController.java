package com.zw.controller;


import com.zw.service.UserInfoService;
import com.zw.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zwq
 * @since 2021-05-25
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/users")
    public String getUsers(){
        return userInfoService.getUsers().toString();
    }
}
