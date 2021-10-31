package com.zw.config;

import com.google.common.collect.Maps;
import com.zw.constants.RabbitMqConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.config
 * @descripation TODO
 * @date 2021-06-24
 */
@Slf4j
@Configuration
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    //队列-直连模式 一对一直连的
    @Bean
    public Queue testDirectQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("TestDirectQueue",true,true,false);

        return new Queue(RabbitMqConst.DIRECT_MODE_QUEUE_ONE, true);
    }

    //-------------------------------------------------------------------
    //主题模式根据后面的routingKey进行区分接受
    @Bean
    public Queue testTopTwoQueue() {
        return new Queue(RabbitMqConst.QUEUE_TWO, true);
    }

    @Bean
    public Queue testTopThreeQueue() {
        return new Queue(RabbitMqConst.QUEUE_THREE, true);
    }

    /**
     * 主题模式队列
     * <li>路由格式必须以 . 分隔，比如 user.email 或者 user.aaa.email</li>
     * <li>通配符 * ，代表一个占位符，或者说一个单词，比如路由为 user.*，那么 user.email 可以匹配，但是 user.aaa.email 就匹配不了</li>
     * <li>通配符 # ，代表一个或多个占位符，或者说一个或多个单词，比如路由为 user.#，那么 user.email 可以匹配，user.aaa.email 也可以匹配</li>
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMqConst.TOPIC_MODE_EXCHANGE);
    }

    //queue.2 路由 主题模式绑定队列2
    @Bean
    public Binding bindingTopTwo() {
        return BindingBuilder.bind(testTopTwoQueue()).to(topicExchange()).with(RabbitMqConst.QUEUE_TWO);
    }

    //queue.# 路由 主题模式绑定队列3
    @Bean
    public Binding bindingTopThree() {
        return BindingBuilder.bind(testTopThreeQueue()).to(topicExchange()).with(RabbitMqConst.TOPIC_ROUTING_KEY_ONE);
    }

    //--------------------------------------------------------------
    //分列模式一旦发送，全部接受
    @Bean
    public Queue testFanoutAQueue() {
        return new Queue(RabbitMqConst.FANOUT_QUEUE_A, true);
    }

    @Bean
    public Queue testFanoutBQueue() {
        return new Queue(RabbitMqConst.FANOUT_QUEUE_B, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMqConst.FANOUT_MODE_EXCHANGE);
    }

    //扇形不需要with路由,不起作用
    @Bean
    public Binding bindFanoutA() {
        return BindingBuilder.bind(testFanoutAQueue()).to(fanoutExchange());
    }

    //扇形不需要with,不起作用
    @Bean
    public Binding bindFanoutB() {
        return BindingBuilder.bind(testFanoutBQueue()).to(fanoutExchange());
    }

    //---------------------------------------------------------------------------------------------
    //延迟

    /**
     * 延迟队列
     */
    @Bean
    public Queue delayQueue() {
        return new Queue(RabbitMqConst.DELAY_QUEUE, true);
    }

    /**
     * 延迟队列交换器, x-delayed-type 和 x-delayed-message 固定
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = Maps.newHashMap();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(RabbitMqConst.DELAY_MODE_EXCHANGE, "x-delayed-message", true, false, args);
    }

    /**
     * 延迟队列绑定自定义交换器
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(RabbitMqConst.DELAY_QUEUE).noargs();
    }

    //--------------------------------------------------------------------------
    //死信

    /**
     * 死信工作队列，绑定死信的交换机和路由，key值固定
     */
    @Bean
    public Queue testDeadWorkQueue() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange    这里声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", RabbitMqConst.DEAD_MODE_EXCHANGE);
        //x-dead-letter-routing-key  这里声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", RabbitMqConst.DEAD_QUEUE_ROUTING_KEY);
//        //声明死信队列中的消息过期时间
//        args.put("x-message-ttl",env.getProperty("pay.mq.ttl",int.class));
        return QueueBuilder.durable(RabbitMqConst.DEAD_WORK_QUEUE).withArguments(args).build();
    }

    /**
     * 绑定死信的工作交换器
     */
    @Bean
    public FanoutExchange testDeadWorkExchange() {
        return new FanoutExchange(RabbitMqConst.DEAD_WORK_EXCHANGE);
    }

    @Bean
    public Binding deadWork() {
        return BindingBuilder.bind(testDeadWorkQueue()).to(testDeadWorkExchange());
    }

    //死信相关就是一个直连的普通队列
    @Bean
    public Queue testDeadQueue() {
        return new Queue(RabbitMqConst.DEAD_QUEUE);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(RabbitMqConst.DEAD_MODE_EXCHANGE);
    }

    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(testDeadQueue()).to(deadLetterExchange()).with(RabbitMqConst.DEAD_QUEUE_ROUTING_KEY);
    }
}
