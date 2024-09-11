package com.mrqinzh.framework.mybatis.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

/**
 * 通用参数填充实现类
 *
 * 如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值
 */
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity entity) {
            Date now = new Date();
            // 创建时间为空，则以当前时间为插入时间
            if (Objects.isNull(entity.getCreateTime())) {
                entity.setCreateTime(now);
            }
            // 更新时间为空，则以当前时间为更新时间
            if (Objects.isNull(entity.getUpdateTime())) {
                entity.setUpdateTime(now);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间为空，则以当前时间为更新时间
        Object modifyTime = getFieldValByName("updateTime", metaObject);
        if (Objects.isNull(modifyTime)) {
            setFieldValByName("updateTime", new Date(), metaObject);
        }

    }
}
