package com.zw.entity.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.entity
 * @descripation TODO
 * @date 2021-06-15
 */
@Component
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtProperty {
    /**
     * jwt 加密 key
     */
    private String key;
    /**
     * jwt 过期时间，默认值：600000 {@code 10 分钟}.
     */
    private Long ttl;
    /**
     * 开启 记住我 之后 jwt 过期时间，默认值 604800000 {@code 7 天}
     */
    private Long remember;
}
