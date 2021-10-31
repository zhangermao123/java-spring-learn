package com.zw;

import com.zw.pojo.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw
 * @descripation TODO
 * @date 2021-06-01
 */
@Slf4j
public class RoleServiceTest extends DemoMybatisPlusApplicationTests{


    @Test
    public void testQueryOne(){
        List<Role> list = new Role().selectAll();
        log.info("[testQueryOne] selectAll : "+list);
    }

}
