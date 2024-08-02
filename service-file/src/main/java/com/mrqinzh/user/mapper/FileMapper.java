package com.mrqinzh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrqinzh.common.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface FileMapper extends BaseMapper<MyFile> {

    @Update("update file set status = 1 where file_name = #{fileName}")
    Boolean deleteStatus(String fileName);

    @Select("select * from file where file_name = #{fileName}")
    MyFile getByFileName(String fileName);

}
