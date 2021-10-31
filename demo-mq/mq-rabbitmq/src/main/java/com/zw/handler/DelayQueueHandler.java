package com.zw.handler;

import cn.hutool.json.JSONUtil;
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
 * @descripation TODO
 * @date 2021-06-25
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConst.DELAY_QUEUE)
public class DelayQueueHandler {

    //这个颜色设置手动关闭队列
    //basic.ack用于肯定确认
    //basic.nack用于否定确认（注意：这是AMQP 0-9-1的RabbitMQ扩展）
    //basic.reject用于否定确认，但与basic.nack相比有一个限制:一次只能拒绝单条消息
    @RabbitHandler
    public void process(MessageStruct messageStruct, Message message, Channel channel){
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("延迟队列，手动ACK，接收消息：{}", JSONUtil.toJsonStr(messageStruct));
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
                //或设置 最后一个true表示继续把拒绝的消息压如队列，设置为false重新退出 应该进入死信 channel.basicNack(deliveryTag, false,true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
