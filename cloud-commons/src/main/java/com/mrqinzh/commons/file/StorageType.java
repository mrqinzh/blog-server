package com.mrqinzh.commons.file;

public enum StorageType {

    local("本地"),
    ftp("ftp"),
    aliyun("阿里云"),
    fastdfs("fastdfs"),
    smb("SMB协议"),
    amazon("亚马逊"),
    hdfs("hdfs"),
    qys3("qys3"),
    obs("华为云obs"),
    cos("腾讯云cos"),
    minio("minIO存储");

    StorageType(String desc) {

    }

}
