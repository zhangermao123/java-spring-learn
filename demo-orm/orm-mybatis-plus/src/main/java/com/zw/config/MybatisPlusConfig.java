package com.zw.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.config
 * @descripation TODO
 * @date 2021-06-01
 */
@Component
@MapperScan(basePackages = {"com.zw.mapper"})
public class MybatisPlusConfig {

    //3.2版本分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

   // 最新版3.4以上版本 分页插件
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
//        return interceptor;
//    }
}
