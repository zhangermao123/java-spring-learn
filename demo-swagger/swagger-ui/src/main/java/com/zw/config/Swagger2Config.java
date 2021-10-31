package com.zw.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.config
 * @descripation TODO
 * @date 2021-06-07
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    public Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                select().// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                apis(RequestHandlerSelectors.basePackage("com.zw.controller")).
                paths(PathSelectors.any()).//配置如何通过path过滤,any() 任何请求都扫描 none() 任何请求都不扫描 regex(final String pathRegex) 通过正则表达式控制  ant (final String antPattern) 通过ant()控制 例如PathSelectors.ant("/demo/**")
                build();
    }

   /* Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱");
   return new ApiInfo(
           "Swagger学习", // 标题
           "学习演示如何配置Swagger", // 描述
           "v1.0", // 版本
           "http://terms.service.url/组织链接", // 组织链接
           contact, // 联系人信息
           "Apach 2.0 许可", // 许可
           "许可链接", // 许可连接
           new ArrayList<>()// 扩展
    );*/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("spring-boot-swagger测试").description("这是一个简单的 Swagger API 演示").contact(new Contact("weizhang", "http://weizhang.com", "xxxxx@qq.com")).version("1.0.0-SNAPSHOT").build();
    }
}
