package com.zw.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @date 2021-06-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "用户实体",description = "User Entity")
public class User implements Serializable {

    private static final long serialVersionUID = 1523692707507670399L;
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", required = true)
    private Integer id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String name;
    /**
     * 工作岗位
     */
    @ApiModelProperty(value = "工作岗位", required = false)
    private String job;
}
