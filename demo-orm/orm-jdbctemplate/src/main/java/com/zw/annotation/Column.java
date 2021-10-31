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
public @interface Column {
    /**
     * 列名
     *
     * @return 列名
     */
    String name();
}
