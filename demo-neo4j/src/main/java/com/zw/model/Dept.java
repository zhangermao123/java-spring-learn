package com.zw.model;

import com.zw.config.CommonIdStrategy;
import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.model
 * @descripation 部门实体
 * @date 2021-06-30
 */
@Data
@Builder
@NodeEntity(label = "dept")
public class Dept {
    @Id
    @GeneratedValue(strategy = CommonIdStrategy.class)
    private String id;

    @Property(name = "deptName")
    private String deptName;
}
