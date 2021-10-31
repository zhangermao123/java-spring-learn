package com.zw.service;

import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.service
 * @descripation TODO
 * @date 2021-06-18
 */
@Service //这个必须指引到dubbo的service不能用spring的不然无法链接zookeeper!
@Component //zookeeper必须添加component
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        log.info("someone is calling me...... [dubbo-provider]");
        return "say hello to "+ name;
    }
}
