package com.zw.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.exception
 * @descripation TODO
 * @date 2021-07-06
 */
@NoArgsConstructor
@AllArgsConstructor
public class LdapException extends RuntimeException{
    private Integer code;
    private String message;
}
