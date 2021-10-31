package com.zw.job;

import lombok.Data;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.job
 * @descripation 执行的任务
 * @date 2021-06-04
 */
@Data
public class QuartzJob extends QuartzJobBean {

    private String author;
    //具体执行的job
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("QuartzJob1 ---- author: " +author +" ------ time: " + new Date());
    }
}
