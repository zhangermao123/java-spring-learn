package com.zw.entity.request;

import lombok.Data;

/**
 * @className PageCondition
 * @description TODD
 * @author zhangwei
 * @date 2021/6/15
 * @version 1.0
 */
@Data
public class PageCondition {
    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页条数
     */
    private Integer pageSize;

}
