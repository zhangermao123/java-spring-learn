package com.zw.entity.property;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.entity.property
 * @descripation TODO
 * @date 2021-06-16
 */
@Data
public class IgnoreConfig {
    /**
     * 需要忽略的 URL 格式，不考虑请求方法
     */
    private List<String> pattern = Lists.newArrayList();

    /**
     * 需要忽略的 GET 请求
     */
    private List<String> get = Lists.newArrayList();

    /**
     * 需要忽略的 POST 请求
     */
    private List<String> post = Lists.newArrayList();

    /**
     * 需要忽略的 DELETE 请求
     */
    private List<String> delete = Lists.newArrayList();

    /**
     * 需要忽略的 PUT 请求
     */
    private List<String> put = Lists.newArrayList();

    /**
     * 需要忽略的 HEAD 请求
     */
    private List<String> head = Lists.newArrayList();

    /**
     * 需要忽略的 PATCH 请求
     */
    private List<String> patch = Lists.newArrayList();

    /**
     * 需要忽略的 OPTIONS 请求
     */
    private List<String> options = Lists.newArrayList();

    /**
     * 需要忽略的 TRACE 请求
     */
    private List<String> trace = Lists.newArrayList();
}
