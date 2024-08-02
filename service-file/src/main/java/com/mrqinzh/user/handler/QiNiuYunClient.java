package com.mrqinzh.user.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrqinzh.common.exception.BizException;
//import com.mrqinzh.core.properties.GlobalProperties;
//import com.qiniu.http.Response;
//import com.qiniu.storage.Configuration;
//import com.qiniu.storage.Region;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QiNiuYunClient extends AbstractFileClient {

    public static final String QINIU_FILE_PRE_KEY = "oss_qiniuyun_";

    private String link;

    @Value("${mrqinzh.blog.oss.domain:}")
    private String domain;
    @Value("${mrqinzh.blog.oss.bucketname:}")
    private String bucketName;
    @Value("${mrqinzh.blog.oss.access-key:}")
    private String accessKey;
    @Value("${mrqinzh.blog.oss.secret-key:}")
    private String secretKey;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doUpload(byte[] file) throws Exception {
//        checkProperties();
//        Configuration cfg = new Configuration(Region.huadong());
//        UploadManager uploadManager = new UploadManager(cfg);
//        Auth auth = Auth.create(accessKey, secretKey);
//        String uploadToken = auth.uploadToken(bucketName);
//
//        Response response = uploadManager.put(file, getNewFileNameWithSuffix(), uploadToken);
//        Map res = objectMapper.readValue(response.bodyString(), Map.class);
//        link = GlobalProperties.MY_HTTP + domain + "/" + res.get("key");
    }

    private void checkProperties() {
        if (StringUtils.isBlank(domain) ||
                StringUtils.isBlank(bucketName) ||
                StringUtils.isBlank(accessKey) ||
                StringUtils.isBlank(secretKey)) {
            throw new BizException("七牛云配置错误，请检查相关配置信息。！");
        }
    }

    @Override
    public String getFileName() {
        return QINIU_FILE_PRE_KEY + UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public String getFileLink() {
        return link;
    }

    @Override
    public FileClientType clientType() {
        return FileClientType.QINIUYUN;
    }
}
