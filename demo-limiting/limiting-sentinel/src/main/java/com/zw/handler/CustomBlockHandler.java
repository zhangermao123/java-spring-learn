package com.zw.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.handler
 * @descripation TODO
 * @date 2021-07-10
 */
public class CustomBlockHandler {

    public static String handlerException(BlockException blockException){
        return "fail is produces : "+ blockException;
    }
}
