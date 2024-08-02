package com.mrqinzh.commons.file;

import java.io.Serializable;
import java.util.Date;

public class FileMeta implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key; // 文件唯一标识

//    private MimeType mimeType; // 文件类型
    private Long size; // 文件大小
    private Date createTime; // 创建时间

    private StorageType storageType;
}
