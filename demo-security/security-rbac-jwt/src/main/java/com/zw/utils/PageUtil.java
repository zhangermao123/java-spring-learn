package com.zw.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import com.zw.contant.ParamKey;
import com.zw.entity.request.PageCondition;
import org.springframework.data.domain.PageRequest;

/**
 * @className PageUtil
 * @description TODD
 * @author zhangwei 
 * @date 2021/6/17
 * @version 1.0
 */
public class PageUtil {
    /**
     * 校验分页参数，为NULL，设置分页参数默认值
     *
     * @param condition 查询参数
     * @param clazz     类
     * @param <T>       {@link PageCondition}
     */
    public static <T extends PageCondition> void checkPageCondition(T condition, Class<T> clazz) {
        if (ObjectUtil.isNull(condition)) {
            condition = ReflectUtil.newInstance(clazz);
        }
        // 校验分页参数
        if (ObjectUtil.isNull(condition.getCurrentPage())) {
            condition.setCurrentPage(ParamKey.DEFAULT_CURRENT_PAGE);
        }
        if (ObjectUtil.isNull(condition.getPageSize())) {
            condition.setPageSize(ParamKey.DEFAULT_PAGE_SIZE);
        }
    }

    /**
     * 根据分页参数构建{@link PageRequest}
     *
     * @param condition 查询参数
     * @param <T>       {@link PageCondition}
     * @return {@link PageRequest}
     */
    public static <T extends PageCondition> PageRequest ofPageRequest(T condition) {
        return PageRequest.of(condition.getCurrentPage(), condition.getPageSize());
    }
}
