package com.mrqinzh.framework.mybatis.id;

import java.util.UUID;

public class UUIDIdGenerator implements IdGenerator {


    @Override
    public Object generateId() {
        return UUID.randomUUID().toString();
    }
}
