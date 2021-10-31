package com.zw.entity.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.entity.property
 * @descripation TODO
 * @date 2021-06-16
 */
@Data
@ConfigurationProperties("custom.config")
@Component
public class CustomProperty {
    /**
     * 不需要拦截的地址
     */
    private IgnoreConfig ignores;
}
