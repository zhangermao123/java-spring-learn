package com.zw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.FindByIndexNameSessionRepository;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.config
 * @descripation TODO
 * @date 2021-06-09
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    FindByIndexNameSessionRepository sessionRepository;

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance(); //不加密
    }

    //设置用户名密码，可以设置d多个
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("javaboy")
                .password("123").roles("admin");
    }

    //用来配置忽略掉的 URL 地址，一般对于静态文件，我们可以采用此操作。
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    /**
     * @description
     * 如果我们使用 XML 来配置 Spring Security ，里边会有一个重要的标签 <http>，HttpSecurity 提供的配置方法 都对应了该标签。
     * authorizeRequests 对应了 <intercept-url>。
     * formLogin 对应了 <formlogin>。
     * and 方法表示结束当前标签，上下文回到HttpSecurity，开启新一轮的配置。
     * permitAll 表示登录相关的页面/接口不要被拦截。
     * 最后记得关闭 csrf ，
     * @param
     * @return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/login.html")
                .permitAll()
                .and()
                .rememberMe()
                .key("test")
                .and()
                .csrf().disable()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);
    }
}
