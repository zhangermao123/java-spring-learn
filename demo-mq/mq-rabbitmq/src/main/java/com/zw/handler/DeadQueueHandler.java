package com.zw.handler;

import com.rabbitmq.client.Channel;
import com.zw.constants.RabbitMqConst;
import com.zw.pojo.MessageStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.handler
 * @descripation 死信队列
 * @date 2021-06-25
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConst.DEAD_QUEUE)
public class DeadQueueHandler {

    @RabbitHandler
    public void process(MessageStruct messageStruct, Message message, Channel channel){
        final long deliverTag = message.getMessageProperties().getDeliveryTag();
        String msg = new String(message.getBody());
        log.info("收到[DeadQueueHandler]业务消息：{} ", msg);
        try {
            channel.basicAck(deliverTag,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
