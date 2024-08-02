package com.mrqinzh.framework.mybatis.id;

import com.mrqinzh.commons.entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Intercepts(
        value = {
                @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class })
        }
)
public class IdGeneratorPlugin implements Interceptor {

    @Autowired
    private AutoIncrIdGenerator idGenerator;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Executor executor = (Executor) invocation.getTarget();
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object param = args[1];

        if (ms.getSqlCommandType() == SqlCommandType.INSERT) {
            BoundSql boundSql = ms.getBoundSql(param);
            // TODO: 2023/3/19 应该可优化
            if (!(param instanceof Collection) && param instanceof BaseEntity) {
                BaseEntity entity = (BaseEntity) param;
                Integer id = entity.getId();
                if (id == null) {
                    entity.setId((Integer) idGenerator.generateId());
                    args[1] = entity;
                    invocation.proceed();
                }
            }
        }

        return invocation.proceed();
    }

}
