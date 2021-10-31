package com.zw.mapper;

import com.zw.pojo.domain.JobAndTrigger;

import java.util.List;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.mapper
 * @descripation TODO
 * @date 2021-06-04
 */
public interface JobMapper {
    /**
     * 查询定时作业和触发器列表
     *
     * @return 定时作业和触发器列表
     */
    List<JobAndTrigger> list();
}
