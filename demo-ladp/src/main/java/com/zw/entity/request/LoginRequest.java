package com.zw.entity.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.entity.request
 * @descripation TODO
 * @date 2021-07-05
 */
@Data
@Builder
public class LoginRequest {
    private String userName;
    private String passWord;
}
