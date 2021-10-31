package com.zw.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;
import java.io.PrintWriter;

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
    DataSource dataSource;

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //不加密
    }

//    //设置用户名密码，可以设置d多个
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("javaboy")
//                .password("123").roles("admin");
//    }

    //对权限范围进行设置，admin包含user，
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_admin > ROLE_user");
        return hierarchy;
    }

//    //内存注入用户并设置角色
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zw").password("123").roles("admin").build());
//        manager.createUser(User.withUsername("java").password("123").roles("user").build());
//        return manager;
//    }

    //内存保存用户并设置角色 使用的org/springframework/security/core/userdetails/jdbc/users.ddl 默认数据结构
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        if (!manager.userExists("zw")) {
            manager.createUser(User.withUsername("zw").password("123").roles("admin").build());
        }
        if(!manager.userExists("java")){
            manager.createUser(User.withUsername("java").password("123").roles("user").build());
        }
        return manager;
    }


    //用来配置忽略掉的 URL 地址，一般对于静态文件，我们可以采用此操作。
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    /**
     * @param
     * @return void
     * @description 如果我们使用 XML 来配置 Spring Security ，里边会有一个重要的标签 <http>，HttpSecurity 提供的配置方法 都对应了该标签。
     * authorizeRequests 对应了 <intercept-url>。
     * formLogin 对应了 <formlogin>。
     * and 方法表示结束当前标签，上下文回到HttpSecurity，开启新一轮的配置。
     * permitAll 表示登录相关的页面/接口不要被拦截。
     * 最后记得关闭 csrf ，
     * <p>
     * 默认注销的 URL 是 /logout，是一个 GET 请求，我们可以通过 logoutUrl 方法来修改默认的注销 URL。
     * logoutRequestMatcher 方法不仅可以修改注销 URL，还可以修改请求方式，实际项目中，这个方法和 logoutUrl 任意设置一个即可。
     * logoutSuccessUrl 表示注销成功后要跳转的页面。
     * deleteCookies 用来清除 cookie。
     * clearAuthentication 和 invalidateHttpSession 分别表示清除认证信息和使 HttpSession 失效，默认可以不用配置，默认就会清除。
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin") //antMatchers 必须在anyRequest前执行
                .antMatchers("/user/**").hasRole("user") //可以设置多个角色
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("user")
                .passwordParameter("passwd")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> { //可以设置这个回调写入用户信息
                    Object principal = authentication.getPrincipal();
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(principal));
                    out.flush();
                    out.close();
                })
//                .defaultSuccessUrl("/index") //其他页面登录成功不跳转index
//                .successForwardUrl("/index") // 默认登录结束后必跳转index
//                .failureUrl("f1")//其他页面登录失败不跳转f1
//                .failureForwardUrl("/f1") //登录失败必跳转f1
                .failureHandler((httpServletRequest, httpServletResponse, e) -> { //返回错误信息
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write(e.getMessage()); //e的类型一般需要处理一下
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .logoutSuccessUrl("/index")
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                      httpServletResponse.setContentType("application/json;charset=utf-8");
//                      PrintWriter out = resp.getWriter();
//                      out.write("注销成功");
//                       out.flush();
//                      out.close();
//                    }
//                })
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
                .and().csrf().disable()
                .sessionManagement()
                .maximumSessions(1) //只允许登录一个设备 此时一个浏览器登录成功后，另外一个浏览器就登录不了了。
                .maxSessionsPreventsLogin(true)
                .and()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> { //处理未正式错误
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write("尚未登录，请先登录");
                            out.flush();
                            out.close();
                        }
                );
    }

    //配合session maxSessionsPreventsLogin使用 为了执行session,如果需要自定义user bean 需要实现 equal和hascode
    @Bean
    HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
