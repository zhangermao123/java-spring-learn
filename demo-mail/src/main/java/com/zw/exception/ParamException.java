package com.zw.exception;


import com.zw.constant.Status;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.exception
 * @descripation TODO
 * @date 2021-05-25
 */
public class ParamException extends BaseException{

    public ParamException() {
        super(Status.PARAM_WRONG);
    }
}
