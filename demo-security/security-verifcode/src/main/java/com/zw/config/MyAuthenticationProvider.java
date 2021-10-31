package com.zw.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.config
 * @descripation
 * @date 2021-06-11
 */

public class MyAuthenticationProvider extends DaoAuthenticationProvider {

    //在执行authenticate验证前进行验证码检查
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String code = request.getParameter("code");
        //通过session传递的code
        String verify_code = (String) request.getSession().getAttribute("verify_code");
        if(!StrUtil.equals(code,verify_code)){
            throw new AuthenticationServiceException("验证码错误");
        }
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
