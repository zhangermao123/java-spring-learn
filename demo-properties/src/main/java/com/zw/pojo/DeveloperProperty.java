package com.zw.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.pojo
 * @descripation TODO
 * @date 2021-05-20
 */
@Data
@Component
@ConfigurationProperties(prefix = "developer-property")
public class DeveloperProperty {
    private String name;
    private String website;
    private String qq;
    private String phoneNumber;

}
