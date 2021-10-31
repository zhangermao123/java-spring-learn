package com.zw.config;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhangwei
 * @version 1.0
 * @className com.zw.config
 * @descripation 在添加数据和更新数据的时候更新TableField 字段
 * @date 2021-06-01
 */
@Slf4j
@Component
public class CommonFileHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.setFieldValByName("createTime",new DateTime(),metaObject);
        this.setFieldValByName("lastUpdateTime",new DateTime(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("lastUpdateTime",new DateTime(),metaObject);
    }
}
