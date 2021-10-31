package com.zw.controller;

import com.zw.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangwei 
 * @version 1.0
 * @className UserController
 * @description TODD
 * @date 2021/5/27
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public ModelAndView login(User user, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        mv.addObject(user);
        mv.setViewName("redirect:/");

        request.getSession().setAttribute("user", user);
        return mv;
    }

    /**
     * @return
     * @description 两种方式 1.使用 ModelAndView
     */
    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("page/login");
    }

    /**
     * @description 2.直接指引路径地址
     * @return java.lang.String
     */
    @GetMapping("/hello-free")
    public String freeIndex(){

        return "page/index-free";
    }
}
