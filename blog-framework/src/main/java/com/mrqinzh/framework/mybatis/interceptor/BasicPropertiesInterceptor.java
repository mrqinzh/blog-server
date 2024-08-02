package com.mrqinzh.framework.mybatis.interceptor;

import com.mrqinzh.commons.entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Intercepts(
        {
                @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class })
        }
)
@Component
public class BasicPropertiesInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        BaseEntity entity = (BaseEntity) args[1];
        SqlCommandType sqlCommandType = ms.getSqlCommandType();

        switch (sqlCommandType) {
            case INSERT:
                if (Objects.isNull(entity.getCreateTime())) {
                    entity.setCreateTime(new Date());
                }
                if (Objects.isNull(entity.getUpdateTime())) {
                    entity.setUpdateTime(new Date());
                }
                break;
            case DELETE:
            case UPDATE:
                if (Objects.isNull(entity.getUpdateTime())) {
                    entity.setUpdateTime(new Date());
                }
                break;
            default:
        }
        return invocation.proceed();
    }
}
