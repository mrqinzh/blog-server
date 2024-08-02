package com.mrqinzh.user.handler;

import lombok.Data;

@Data
public class QiniuCloudOssProperties {

    private String domain;
    private String bucketName;
    private String accessKey;
    private String secretKey;
}
