package com.mrqinzh.user.handler;

import com.mrqinzh.common.constant.FileConstant;
import com.mrqinzh.common.enums.FileSourceType;
import com.mrqinzh.common.enums.MimeType;
import com.mrqinzh.common.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;

public abstract class AbstractFileClient {

    private Logger log = LoggerFactory.getLogger(getClass());

    private String folderPath;
    private String fileSuffix;
    private String newFileNameWithSuffix;

    public final FileInfo upload(byte[] file, MimeType mimeType, FileSourceType fileSourceType) {
        freshProperties(mimeType, fileSourceType);
        FileInfo res = null;
        try {
            doUpload(file);
            res = new FileInfo();
//            res.setFileSize(FileUtil.getFileSize(file.getSize()));
            res.setFileType(fileSuffix);
            res.setFolderPath(folderPath);
            res.setFileName(newFileNameWithSuffix);

            res.setFileClient(clientType());
            res.setFileLink(getFileLink());
        } catch (Exception e) {
            log.error(this.getClass().getName() + "上传文件失败", e);
        }
        return res;
    }

    private void freshProperties(MimeType mimeType, FileSourceType source) {
        this.newFileNameWithSuffix = getFileName() + mimeType.name();
        String sourcePlace = source.name().endsWith("/") ? source.name() : source.name() + File.separator;
        this.folderPath = FileConstant.BASE_FOLDER_PATH +
                sourcePlace +
                FileConstant.filePathFormat.format(new Date()) + File.separator;
        FileUtil.checkFilePath(folderPath);
    }

    public abstract void doUpload(byte[] file) throws Exception;
    public abstract String getFileName();
    public abstract String getFileLink();
    public abstract FileClientType clientType();

    public String getFolderPath() {
        return folderPath;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public String getNewFileNameWithSuffix() {
        return newFileNameWithSuffix;
    }
}
