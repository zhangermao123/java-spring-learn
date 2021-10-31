package com.zw.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.pojo
 * @descripation TODO
 * @date 2021-06-01
 */
@Data
@Component
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    private String uploadDir;
}
