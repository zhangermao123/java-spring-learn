package com.zw.constant;

import com.sun.deploy.net.HttpResponse;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.constant
 * @descripation TODO
 * @date 2021-05-25
 */
@Getter
public enum Status {
    OK(200,"操作成功"),

    NET_ERROR(500,"网络错误"),

    PARAM_WRONG(2, "参数错误"),

    JSON_ERROR(403,"json解析错误");

    private Integer code;
    private String message;

    Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
