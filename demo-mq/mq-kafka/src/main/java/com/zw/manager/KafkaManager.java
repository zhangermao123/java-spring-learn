package com.zw.manager;

import com.zw.constants.KafkaConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.manager
 * @descripation TODO
 * @date 2021-06-28
 */
@Component
@Slf4j
public class KafkaManager {

    @KafkaListener(topics = KafkaConst.TOPIC_MQ,containerFactory = "myFilterContainerFactory")
    public void onMessage(String value){
        log.info("收到消息: {}", value);
//        try {
//            String message = (String) record.value();
//            log.info("收到消息: {}", message);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            // 手动提交 offset
//            acknowledgment.acknowledge();
//        }
    }
}
