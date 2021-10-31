package com.zw.config;

import com.zw.constants.KafkaConst;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.config
 * @descripation TODO
 * @date 2021-06-28
 */
@Configuration
//@EnableConfigurationProperties({KafkaProperties.class})
//@AllArgsConstructor
//@EnableKafka
//@Slf4j
public class KafkaConfig {
    // 监听器工厂
    @Autowired
    private ConsumerFactory consumerFactory;

    // 配置一个消息过滤策略
    @Bean
    public ConcurrentKafkaListenerContainerFactory myFilterContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory =
                new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory);
        // 被过滤的消息将被丢弃
        factory.setAckDiscarded(true);
        // 消息过滤策略（将消息转换为long类型，判断是奇数还是偶数，把所有奇数过滤，监听器只接收偶数）
        factory.setRecordFilterStrategy(new RecordFilterStrategy() {
            @Override
            public boolean filter(ConsumerRecord consumerRecord) {
                long data = Long.parseLong((String) consumerRecord.value());
                if (data % 2 == 0) {
                    return false;
                }
                //返回true将会被丢弃
                return true;
            }
        });
        return factory;
    }



}
