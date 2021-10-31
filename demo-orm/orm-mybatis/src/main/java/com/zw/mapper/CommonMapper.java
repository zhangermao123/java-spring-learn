package com.zw.mapper;

import com.zw.pojo.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.mapper
 * @descripation TODO
 * @date 2021-05-31
 */
@Component
public interface CommonMapper extends Mapper<User>, MySqlMapper<User> {
    
}
