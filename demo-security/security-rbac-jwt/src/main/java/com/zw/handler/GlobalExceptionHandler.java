package com.zw.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.zw.contant.Status;
import com.zw.entity.response.ApiResponse;
import com.zw.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.handler
 * @descripation TODO
 * @date 2021-06-15
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements ResponseBodyAdvice<ApiResponse> {

    @ExceptionHandler(value = Exception.class)
    public ApiResponse handlerException(Exception e) {
        if (e instanceof NoHandlerFoundException) {
            log.error("【全局异常拦截】NoHandlerFoundException: 请求方法 {}, 请求路径 {}", ((NoHandlerFoundException) e).getRequestURL(), ((NoHandlerFoundException) e).getHttpMethod());
            return ApiResponse.ofStatus(Status.REQUEST_NOT_FOUND);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            log.error("【全局异常拦截】HttpRequestMethodNotSupportedException: 当前请求方式 {}, 支持请求方式 {}", ((HttpRequestMethodNotSupportedException) e).getMethod(), JSONUtil.toJsonStr(((HttpRequestMethodNotSupportedException) e).getSupportedHttpMethods()));
            return ApiResponse.ofStatus(Status.HTTP_BAD_METHOD);
        } else if (e instanceof MethodArgumentNotValidException) {
            log.error("【全局异常拦截】MethodArgumentNotValidException", e);
            return ApiResponse.of(Status.BAD_REQUEST.getCode(), ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage(), null);
        } else if (e instanceof ConstraintViolationException) {
            log.error("【全局异常拦截】ConstraintViolationException", e);
            return ApiResponse.of(Status.BAD_REQUEST.getCode(), CollUtil.getFirst(((ConstraintViolationException) e).getConstraintViolations()).getMessage(), null);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            log.error("【全局异常拦截】MethodArgumentTypeMismatchException: 参数名 {}, 异常信息 {}", ((MethodArgumentTypeMismatchException) e).getName(), ((MethodArgumentTypeMismatchException) e).getMessage());
            return ApiResponse.ofStatus(Status.PARAM_NOT_MATCH);
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("【全局异常拦截】HttpMessageNotReadableException: 错误信息 {}", ((HttpMessageNotReadableException) e).getMessage());
            return ApiResponse.ofStatus(Status.PARAM_NOT_NULL);
        } else if (e instanceof BadCredentialsException) {
            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
            return ApiResponse.ofStatus(Status.USERNAME_PASSWORD_ERROR);
        } else if (e instanceof DisabledException) {
            log.error("【全局异常拦截】BadCredentialsException: 错误信息 {}", e.getMessage());
            return ApiResponse.ofStatus(Status.USER_DISABLED);
        } else if (e instanceof BaseException) {
            log.error("【全局异常拦截】DataManagerException: 状态码 {}, 异常信息 {}", ((BaseException) e).getCode(), e.getMessage());
            return ApiResponse.ofException((BaseException) e);
        }

        log.error("【全局异常拦截】: 异常信息 {} ", e.getMessage());
        return ApiResponse.ofStatus(Status.ERROR);
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }


    @Override
    public ApiResponse beforeBodyWrite(ApiResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("beforeBodyWrite=====================>start");
        //返回null 提示请求异常
        if(ObjectUtils.isEmpty(body)){
            return ApiResponse.ofException(new BaseException(Status.BAD_REQUEST));
        }
        //返回baseResponse
        if(body instanceof ApiResponse){
            return (ApiResponse) body;
        }
        log.info("beforeBodyWrite=====================>end");
        return ApiResponse.ofSuccess(body);
    }
}
