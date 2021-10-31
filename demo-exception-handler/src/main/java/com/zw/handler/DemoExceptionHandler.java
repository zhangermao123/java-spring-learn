package com.zw.handler;

import com.zw.constant.Status;
import com.zw.exception.BaseException;
import com.zw.exception.NetException;
import com.zw.pojo.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.handler
 * @descripation TODO
 * @date 2021-05-25
 */
@RestControllerAdvice
@Slf4j
public class DemoExceptionHandler implements ResponseBodyAdvice<Object> {

    //先执行ExceptionHandler 在执行beforeBodyWrite
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BaseResponse>  exceptionHandler(Exception exception){
        log.info("exceptionHandler=====================>begin");
        log.error(exception.getMessage());
        BaseResponse baseResponse = new BaseResponse();
        if(exception instanceof MethodArgumentNotValidException){
            baseResponse.setCode(Status.PARAM_WRONG.getCode());
            baseResponse.setMessage(((MethodArgumentNotValidException) exception).getBindingResult().getFieldError()
                    .getDefaultMessage());
        }else if(exception instanceof MissingServletRequestParameterException){
            baseResponse.setCode(Status.PARAM_WRONG.getCode());
            baseResponse.setMessage(exception.getMessage());
        }else if(exception instanceof BaseException){
            baseResponse.setCode(((BaseException) exception).getCode());
            baseResponse.setMessage(((BaseException) exception).getMessage());
        }else{
            baseResponse.setCode(Status.NET_ERROR.getCode());
            baseResponse.setMessage(Status.NET_ERROR.getMessage());
        }
        log.info("exceptionHandler=====================>end");
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }


    /**
     * @methodName supports
     * @description 判断是否要执行beforeBodyWrite方法，true为执行，false不执行
     * @param returnType MethodParameter
     * @param converterType Class
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    //返回参数类型是BaseResponse,入参是各类
    @Override
    public BaseResponse beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("beforeBodyWrite=====================>start");
        //返回null 提示网络错误
        if(ObjectUtils.isEmpty(body)){
            return BaseResponse.ofException(new NetException());
        }
        //返回baseResponse
        if(body instanceof BaseResponse){
            return (BaseResponse) body;
        }
        log.info("beforeBodyWrite=====================>end");
        return BaseResponse.ofSuccess(body);
    }
}
