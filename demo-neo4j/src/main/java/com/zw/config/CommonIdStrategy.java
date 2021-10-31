package com.zw.config;

import cn.hutool.core.util.IdUtil;
import org.neo4j.ogm.id.IdStrategy;

/**
 * @author zhangwei 
 * @version 1.0
 * @className com.zw.config
 * @descripation UUID生成id
 * @date 2021-06-30
 */
public class CommonIdStrategy implements IdStrategy {
    @Override
    public Object generateId(Object o) {
        return IdUtil.fastUUID();
    }
}
