package com.zw.repository;

import com.zw.model.Dept;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.repository
 * @descripation 节点的Repository
 * @date 2021-06-30
 */
@Repository
public interface DeptRepository extends Neo4jRepository<Dept,Long> {
}
