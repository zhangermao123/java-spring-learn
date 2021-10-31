package com.zw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.pojo
 * @descripation TODO
 * @date 2021-06-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToEmail implements Serializable {
    /**
     * 邮件接收方，可多人
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
}
