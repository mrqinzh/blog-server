package com.mrqinzh.auth.domain.convert;

import com.mrqinzh.auth.domain.bo.LoginLogBO;
import com.mrqinzh.auth.domain.entity.LoginLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginConvert {

    LoginConvert INSTANCE = Mappers.getMapper(LoginConvert.class);

    LoginLogBO convert2LogBO(LoginLog loginLog);

}
