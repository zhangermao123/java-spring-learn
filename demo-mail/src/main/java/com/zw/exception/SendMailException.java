package com.zw.exception;

import com.zw.constant.Status;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.exception
 * @descripation TODO
 * @date 2021-06-03
 */
public class SendMailException extends BaseException{

    public SendMailException() {
        super(Status.MAIL_ERROR);
    }
}
