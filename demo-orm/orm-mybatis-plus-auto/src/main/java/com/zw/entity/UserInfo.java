package com.zw.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zwq
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 钉钉上的userid
     */
    private String userId;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 姓名
     */
    private String uName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 座机号
     */
    private String telephone;

    /**
     * 入职日期
     */
    private Date hiredDate;

    /**
     * 离职日期
     */
    private Date leaveDate;

    /**
     * 创建时间
     */
    private Date createTime;


}
