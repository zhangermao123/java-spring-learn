package com.zw.exception;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.exception
 * @descripation TODO
 * @date 2021-06-01
 */
public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
