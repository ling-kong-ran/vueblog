package com.ling.vblog.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class MybatisObjectHandler implements MetaObjectHandler {
    //配置自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime",Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),metaObject);
        setFieldValByName("updateTime",Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),metaObject);
    }
}
