package com.zw.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author weizhang
 * @since 2021-06-15
 */
@Data
@Accessors(chain = true)
public class SecRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色主键
     */
    private Long roleId;

    /**
     * 权限主键
     */
    private Long permissionId;


}
