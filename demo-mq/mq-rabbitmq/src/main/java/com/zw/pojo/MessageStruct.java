package com.zw.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.pojo
 * @descripation TODO
 * @date 2021-06-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageStruct implements Serializable {

    private static final long serialVersionUID = 5175533903846851475L;

    private String message;
}
