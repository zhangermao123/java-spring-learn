package com.zw.exception;

import com.zw.constant.Status;
import lombok.Data;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.exception
 * @descripation TODO
 * @date 2021-05-25
 */
@Data
public class BaseException extends RuntimeException{
    private Integer code;
    private String message;

    public BaseException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
