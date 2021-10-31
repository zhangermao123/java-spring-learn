###spring boot 学习

> 要查找相关配置文件 通过全局搜索spring.factories 找到spring-boot-autoconfigure的文件
> 搜索对应xxxAutoConfiguration ，进入xxxAutoConfiguration 找到@EnableConfigurationProperties(xxxProperties.class)
> 进入xxxProperties 设置相关参数，这个需要符合map-underscore-to-camel-case 驼峰转下划线 

spring boot 相关学习
包括：

        <!--  hello World -->
        <module>demo-hello</module>
        <!--  配置获取 -->
        <module>demo-properties</module>
        <!--  获取spring boot项目的启动和运行状态 -->
        <module>demo-actuator</module>
        <!--  admin 监控管理 spring boot项目 分成server 和client -->
        <module>demo-admin</module>
        <!--  spring boot log设置-->
        <module>demo-logback</module>
        <!--  切面分析Aspect spring boot项目web请求 log-->
        <module>demo-log-aop</module>
        <!--  spring boot项目 web 统一数据返回和统一错误处理-->
        <module>demo-exception-handler</module>
        <!--  spring boot 使用的前端模板主要使用thymeleaf和freemarker-->
        <module>demo-template</module>
        <!--  spring boot 项目中常用数据库 主要是mybatis，mybatis-plus和BeetSql-->
        <module>demo-orm</module>
        <!-- spring boot 项目上传下载功能-->
        <module>demo-upload</module>
        <!-- spring boot redis缓存使用-->
        <module>demo-redis</module>
        <!-- spring boot 邮件-->
        <module>demo-mail</module>
        <!-- spring boot 定时任务-->
        <module>demo-task</module>
        <!-- spring boot 代码注释-->
        <module>demo-swagger</module>
        <!-- spring boot 登录验证token-->
        <module>demo-security</module>
        <!-- spring boot 通过redis实现session共享-->
        <module>demo-session</module>
        <!-- spring boot dubbo和zookeeper的使用-->
        <module>demo-dubbo-zookeeper</module>
        <!-- spring boot 消息队列-->
        <module>demo-mq</module>
