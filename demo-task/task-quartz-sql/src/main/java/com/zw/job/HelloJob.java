package com.zw.job;

import cn.hutool.core.date.DateUtil;
import com.zw.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.job
 * @descripation 具体实现job接口
 * @date 2021-06-07
 */
@Slf4j
public class HelloJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("[HelloJob]*************************"+ DateUtil.now());
    }
}
