package com.zw.controller;

import cn.hutool.core.date.DateUtil;
import com.zw.constants.RabbitMqConst;
import com.zw.pojo.MessageStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.controller
 * @descripation TODO
 * @date 2021-06-24
 */
@RestController
@Slf4j
public class RabbitMqController {
    @Autowired
    public RabbitTemplate rabbitTemplate;

    /**
     * 测试直接模式发送 交换机是AMQP default(直连交换机)
     * 直连交换机是一对一,direct轮询消费消息队列的发送
     */
    @GetMapping("/sendDirect")
    public String sendDirect(){
        log.info(">>>>>>>>>>[sendDirect]");
        rabbitTemplate.convertAndSend(RabbitMqConst.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message"));
        return "direct";
    }


    /**
     * 主题交换机 发送的key queue.2
     */
    @GetMapping("/sendTop2")
    public String sendTop2(){
        log.info(">>>>>>>>>>[sendTop2]");
        rabbitTemplate.convertAndSend(RabbitMqConst.TOPIC_MODE_EXCHANGE,RabbitMqConst.QUEUE_TWO, new MessageStruct("top sendTopExchange2"));
        return "topic";
    }


    /**
     * 主题交换机 发送的queue.3
     */
    @GetMapping("/sendTop3")
    public String sendTop3(){
        log.info(">>>>>>>>>>[sendTop3]");
        rabbitTemplate.convertAndSend(RabbitMqConst.TOPIC_MODE_EXCHANGE,RabbitMqConst.QUEUE_THREE, new MessageStruct("top sendTopExchange3"));
        return "topic";
    }


    /**
     * 扇形交换机
     */
    @GetMapping("/sendFount")
    public String sendFount(){
        log.info(">>>>>>>>>>[sendFount]");
        rabbitTemplate.convertAndSend(RabbitMqConst.FANOUT_MODE_EXCHANGE,null, new MessageStruct("send fountmessage"));
        return "fount";
    }

    /**
     * 延迟交换机 ,格式固定，必须是x-delay
     */
    @GetMapping("/sendDelay")
    public String sendDelay(){
        log.info(">>>>>>>>>>[sendDelay]");
        rabbitTemplate.convertAndSend(RabbitMqConst.DELAY_MODE_EXCHANGE, RabbitMqConst.DELAY_QUEUE, new MessageStruct("delay message, delay 5s, " + DateUtil.date()), message -> {
            message.getMessageProperties().setHeader("x-delay", 5000);
            return message;
        });

        rabbitTemplate.convertAndSend(RabbitMqConst.DELAY_MODE_EXCHANGE, RabbitMqConst.DELAY_QUEUE, new MessageStruct("delay message, delay 2s, " + DateUtil.date()), message -> {
            message.getMessageProperties().setHeader("x-delay", 2000);
            return message;
        });

        rabbitTemplate.convertAndSend(RabbitMqConst.DELAY_MODE_EXCHANGE, RabbitMqConst.DELAY_QUEUE, new MessageStruct("delay message, delay 8s, " + DateUtil.date()), message -> {
            message.getMessageProperties().setHeader("x-delay", 8000);
            return message;
        });
        return "delay";
    }

    /**
     * 死信
     */
    @GetMapping("/sendDead")
    public String sendDead(){
        log.info(">>>>>>>>>>[sendDead]");
        rabbitTemplate.convertAndSend(RabbitMqConst.DEAD_WORK_EXCHANGE,null, new MessageStruct("send 死信"));
        return "sendDead";
    }
}
