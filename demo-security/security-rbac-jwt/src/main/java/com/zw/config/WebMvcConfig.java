package com.zw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className WebMvcConfig
 * @description 设置跨域 包括设置addCorsMappings和SecurityConfig中的http.cors()
 * @author zhangwei 
 * @date 2021/6/16
 * @version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            // 设置允许跨域请求的域名
            .allowedOrigins("*")
            // 设置允许的方法
            .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
            // 跨域允许时间
            .maxAge(MAX_AGE_SECS);
    }
}
