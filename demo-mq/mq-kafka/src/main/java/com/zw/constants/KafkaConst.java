package com.zw.constants;

import org.springframework.stereotype.Component;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.constants
 * @descripation TODO
 * @date 2021-06-28
 */
public interface KafkaConst {
    /**
     * 默认分区大小
     */
    Integer DEFAULT_PARTITION_NUM = 3;

    /**
     * Topic 名称
     */
    String TOPIC_MQ = "zwmq";
}
