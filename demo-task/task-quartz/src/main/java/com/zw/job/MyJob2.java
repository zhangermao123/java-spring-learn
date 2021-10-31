package com.zw.job;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.job
 * @descripation TODO
 * @date 2021-06-04
 */
@Component
public class MyJob2 {
    public void sayHello() {
        System.out.println("MyJob2-----"+new Date());
    }
}
