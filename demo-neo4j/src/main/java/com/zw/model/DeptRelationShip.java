package com.zw.model;

import com.zw.config.CommonIdStrategy;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.model
 * @descripation 关系实体
 * @date 2021-06-30
 */
@RelationshipEntity(type = "deptRelationship")
@Data
@Builder
public class DeptRelationShip {

    /**
     * 主键，自定义主键策略，由于neo4j默认id自增，无法删除这么设置
     */
    @Id
    @GeneratedValue(strategy = CommonIdStrategy.class)
    private String id;

    /**
     * 父节点
     */
    @StartNode
    private Dept parent;

    /**
     * 子节点
     */
    @EndNode
    private Dept child;
}
