package com.zw.util;

import com.zw.job.base.BaseJob;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.util
 * @descripation TODO
 * @date 2021-06-07
 */
public class JobUtil {

    /**
     * @description TODD
     * @param className 文件名 {@link String}
     * @return com.zw.job.base.BaseJob
     */
    public static BaseJob getClass(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return (BaseJob) clazz.newInstance();
    }
}
