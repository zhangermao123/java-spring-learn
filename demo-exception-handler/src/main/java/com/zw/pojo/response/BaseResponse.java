package com.zw.pojo.response;

import com.zw.constant.Status;
import com.zw.exception.BaseException;
import lombok.Data;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.pojo.response
 * @descripation TODO
 * @date 2021-05-25
 */
@Data
public class BaseResponse<T> {
    private Integer code;
    private String message;
    private T data;

    /**
     * @methodName BaseResponse
     * @descriptio 无参构造函数
     */
    public BaseResponse() {

    }

    /**
     * 全参构造函数
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     */
    private BaseResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @description of方法
     * @param code Integer
     * @param message String
     * @param data T
     * @return com.zw.pojo.response.BaseResponse
     */
    private static <T> BaseResponse of(Integer code, String message, T data){

       return new BaseResponse(code,message,data);
    }


    /**
     * @description 状态of方法
     * @param status
     * @param data
     * @return com.zw.pojo.response.BaseResponse
     */
    private static <T> BaseResponse ofStatus(Status status, T data){

        return of(status.getCode(),status.getMessage(),data);
    }

    /**
     * @description 成功
     * @param data
     * @return com.zw.pojo.response.BaseResponse
     */
    public static <T> BaseResponse ofSuccess(T data){
        return ofStatus(Status.OK,data);
    }

    /**
     * @description 错误
     * @param ext
     * @return com.zw.pojo.response.BaseResponse
     */
    public static <T extends BaseException> BaseResponse ofException(T ext){
        return of(ext.getCode(),ext.getMessage(),null);
    }


}
