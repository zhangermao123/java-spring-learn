package com.zw.controller;

import com.zw.constants.KafkaConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-28
 */
@RestController
@Slf4j
public class KafkaController {
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    @GetMapping("/sendKafka")
    public String sendKafka(){
        log.info(">>>>>>>>>kfka 发送消息");
        for (int i = 0; i < 6; i++) {
            kafkaTemplate.send(KafkaConst.TOPIC_MQ,i+"");
        }

        return "send success";
    }

}
