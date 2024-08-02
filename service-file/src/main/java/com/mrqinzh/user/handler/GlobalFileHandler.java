package com.mrqinzh.user.handler;

import com.mrqinzh.common.enums.FileSourceType;
import com.mrqinzh.common.enums.MimeType;
import com.mrqinzh.common.utils.BizAssert;
import com.mrqinzh.framework.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalFileHandler {

    @Value("${mrqinzh.blog.oss.client:LOCAL}")
    private String fileClient;

    public FileInfo upload(byte[] file, MimeType mimeType, FileSourceType fileSourceType) {
        BizAssert.notNull(file, "file is not null !");
        BizAssert.notNull(fileSourceType, "source is not null !");
        AbstractFileClient fileClient = getFileClient();
        return fileClient.upload(file, mimeType, fileSourceType);
    }

    private AbstractFileClient getFileClient() {
        FileClientType fileClientType = null;
        try {
            fileClientType = FileClientType.valueOf(fileClient);
        } catch (IllegalArgumentException e) {
            return new LocalFileClient();
        }
        // TODO: 2023/3/28 实现 initialBean
        switch (fileClientType) {
            case QINIUYUN:
                return SpringContextHolder.getBean(QiNiuYunClient.class);
            case LOCAL:
            default:
                return SpringContextHolder.getBean(LocalFileClient.class);
        }
    }

}
