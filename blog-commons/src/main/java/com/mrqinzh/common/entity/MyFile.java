package com.mrqinzh.common.entity;

import com.mrqinzh.commons.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class MyFile extends BaseEntity {

    private String fileName;

    private String filePath;

    private String fileType;

    private Date fileCreateTime;

    private String fileSize;

    private String filePlace;

    private Integer status;

}
