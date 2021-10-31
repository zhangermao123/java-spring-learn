package com.zw.handler;

import com.zw.constants.RabbitMqConst;
import com.zw.pojo.MessageStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.handler
 * @descripation TODO
 * @date 2021-06-24
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConst.DIRECT_MODE_QUEUE_ONE)
public class DirectQueueOneHandler {

    @RabbitHandler
    public void directHandlerAuto(MessageStruct messageStruct){
        log.info("第一个>>>>>>>>>>[DirectQueueOneHandler] auto message : "+ messageStruct);
    }
}
