package com.mrqinzh.user.dubbo;

import com.mrqinzh.apis.file.FileFacedService;
import com.mrqinzh.common.enums.FileSourceType;
import com.mrqinzh.common.enums.MimeType;
import com.mrqinzh.common.exception.FileServiceException;
import com.mrqinzh.user.file.BaseStorageService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class FileFacedServiceImpl implements FileFacedService {

    @Resource
    private BaseStorageService uploadService;

    @Override
    public String upload(byte[] fileBytes, MimeType mimeType, FileSourceType fileSourceType) throws FileServiceException {
        return uploadService.upload(fileBytes, mimeType, fileSourceType);
    }

    @Override
    public byte[] download(String fileKey) {
        return uploadService.download(fileKey);
    }

}
