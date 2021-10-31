package com.zw.dao;

import com.zw.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.dao
 * @descripation TODO
 * @date 2021-06-10
 */
public interface RoleDao extends JpaRepository<Role,Long>{

}
