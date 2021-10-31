package com.zw.config;

import com.zw.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.config
 * @descripation TODO
 * @date 2021-06-17
 */
@Component
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置拦截器执行controller 方法,添加拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(sessionInterceptor);
        //不需要拦截逻辑
        interceptorRegistration.excludePathPatterns("/page/login","/page/doLogin");
        // 需要拦截的路径
        interceptorRegistration.addPathPatterns("/**");
    }
}
