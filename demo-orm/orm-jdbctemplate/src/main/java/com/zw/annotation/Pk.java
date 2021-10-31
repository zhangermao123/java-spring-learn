package com.zw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.annotation
 * @descripation TODO
 * @date 2021-05-28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Pk {
    /**
     * 自增
     *
     * @return 自增主键
     */
    boolean auto() default true;
}
