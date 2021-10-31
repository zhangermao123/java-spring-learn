package com.zw.repository;

import com.zw.model.DeptRelationShip;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.repository
 * @descripation 关系线路re
 * @date 2021-06-30
 */
@Repository
public interface RelationShipRepository extends Neo4jRepository<DeptRelationShip,Long> {
}
