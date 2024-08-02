package com.mrqinzh.user.handler;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.util.UUID;

@Component
public class LocalFileClient extends AbstractFileClient {

    public static final String LOCAL_FILE_PRE_KEY = "oss_local_";

    @Override
    public void doUpload(byte[] file) throws Exception {
        String realFilePath = getFolderPath() + getNewFileNameWithSuffix(); // 相对路径 /files/xxxx/xx.jpg
        File realFile = new File(realFilePath);
        FileCopyUtils.copy(file, realFile);
    }

    @Override
    public String getFileName() {
        return LOCAL_FILE_PRE_KEY + UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public String getFileLink() {
        return getFolderPath() + getNewFileNameWithSuffix(); // 获取时，进行计算
    }

    @Override
    public FileClientType clientType() {
        return FileClientType.LOCAL;
    }

}
