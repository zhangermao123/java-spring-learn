package com.zw.constants;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.constants
 * @descripation TODO
 * @date 2021-06-24
 */
public interface RabbitMqConst {
    /**
     * 直接模式1
     */
    String DIRECT_MODE_QUEUE_ONE = "queue.direct.1";

    /**
     * 队列2
     */
    String QUEUE_TWO = "queue.2";

    /**
     * 队列3
     */
    String QUEUE_THREE = "queue.3";

    /**
     * 路由
     */
    String TOPIC_ROUTING_KEY_ONE = "queue.#";

    /**
     * 主题模式
     */
    String TOPIC_MODE_EXCHANGE = "topic.mode";



    /**
     * 队列2
     */
    String FANOUT_QUEUE_A = "fanout.queue.a";

    /**
     * 队列3
     */
    String FANOUT_QUEUE_B = "fanout.queue.b";

    /**
     * 扇型模式
     */
    String FANOUT_MODE_EXCHANGE = "fanout.mode";


    /**
     * 延迟队列
     */
    String DELAY_QUEUE = "delay.queue";

    /**
     * 延迟队列交换器
     */
    String DELAY_MODE_EXCHANGE = "delay.mode";

    /**
     * 死信工具队列
     */
    String DEAD_WORK_QUEUE = "dead.work.queue";

    /**
     * 死信工具交换机
     */
    String DEAD_WORK_EXCHANGE = "dead.work.exchange";

    /**
     * 死信队列
     */
    String DEAD_QUEUE = "dead.queue";

    /**
     * 死信路由
     */
    String DEAD_QUEUE_ROUTING_KEY = "dead.queue.routing.key";

    /**
     * 死信交换器
     */
    String DEAD_MODE_EXCHANGE = "dead.mode";

}
