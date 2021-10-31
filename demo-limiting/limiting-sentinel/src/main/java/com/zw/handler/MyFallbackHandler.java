package com.zw.handler;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.handler
 * @descripation TODO
 * @date 2021-07-10
 */
public class MyFallbackHandler {

    public static String getFallback(String id,Throwable t){
        return "[getFallback]  id : "+id + " throwable :"+ t;
    }
}
