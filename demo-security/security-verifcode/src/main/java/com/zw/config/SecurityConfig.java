package com.zw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.config
 * @descripation TODO
 * @date 2021-06-09
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //通过myAuthenticationProvider 设置passwordEncoder；
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance(); //不加密
    }

    //通过myAuthenticationProvider设置UserDetailsService
    @Bean
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zw").password("123").roles("admin").build());
        return manager;
    }

    //将myAuthenticationProvider注入AuthenticationManager
    @Bean
    protected AuthenticationManager authenticationManager(){
        MyAuthenticationProvider myAuthenticationProvider = new MyAuthenticationProvider();
        myAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        myAuthenticationProvider.setUserDetailsService(userDetailsService());
        ProviderManager providerManager = new ProviderManager(Arrays.asList(myAuthenticationProvider));
        return providerManager;
    }

    //设置验证码相关参数,高宽字符库以及生成的验证码字符长度
    @Bean
    Producer verifyCode() {
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width", "150");
        properties.setProperty("kaptcha.image.height", "50");
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

    //用来配置忽略掉的 URL 地址，一般对于静态文件，我们可以采用此操作。没有可注释
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
                .antMatchers("/vc.jpg").permitAll() //这种都可执行vc.jpg，不被拦截
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successForwardUrl("/index")
                .failureForwardUrl("/error")
                .permitAll()
                .and()
                .csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/vc.jpg").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .successHandler((req, resp, auth) -> {
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write(new ObjectMapper().writeValueAsString(auth.getPrincipal()));
//                    out.flush();
//                    out.close();
//                })
//                .failureHandler((req, resp, e) -> {
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write(new ObjectMapper().writeValueAsString(e.getMessage()));
//                    out.flush();
//                    out.close();
//                })
//                .permitAll()
//                .and()
//                .csrf().disable();
    }

}
