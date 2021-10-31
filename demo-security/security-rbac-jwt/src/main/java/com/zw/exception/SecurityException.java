package com.zw.exception;

import com.zw.contant.Status;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.exception
 * @descripation TODO
 * @date 2021-06-15
 */
public class SecurityException extends BaseException{

    public SecurityException(Status status) {
        super(status);
    }
}
