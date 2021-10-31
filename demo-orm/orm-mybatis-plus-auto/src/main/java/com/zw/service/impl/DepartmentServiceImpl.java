package com.zw.service.impl;

import com.zw.entity.Department;
import com.zw.mapper.DepartmentMapper;
import com.zw.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weizhang
 * @since 2021-05-25
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
