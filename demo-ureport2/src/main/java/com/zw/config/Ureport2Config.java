package com.zw.config;

import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.config
 * @descripation TODO
 * @date 2021-07-12
 */
@ImportResource("classpath:ureport-console-context.xml")
@Configuration
public class Ureport2Config implements BuildinDatasource {
    @Autowired
    DataSource dataSource;

    @Override
    public String name() {
        return "ureport2 内部数据";
    }

    @Bean //定义ureport的启动servlet
    public ServletRegistrationBean buildUreportServlet(){
        return new ServletRegistrationBean(new UReportServlet(),"/ureport/*");//  /ureport/* 不可更改
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
