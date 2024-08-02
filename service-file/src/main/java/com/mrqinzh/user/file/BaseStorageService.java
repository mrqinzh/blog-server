package com.mrqinzh.user.file;

import com.mrqinzh.common.enums.FileSourceType;
import com.mrqinzh.common.enums.MimeType;
import com.mrqinzh.common.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.UUID;

@Service
public class BaseStorageService {

    @Resource
    private FileService fileService;

    public String upload(byte[] fileBytes, MimeType mimeType, FileSourceType fileSourceType) {
        String path = computePath(fileSourceType);
        String fileName = generateFileName();
        return null;
    }

    public byte[] download(String fileKey) {
        return new byte[0];
    }

    /**
     * @return /Article/20232328/
     */
    private String computePath(FileSourceType fileSourceType) {
        return File.separator + fileSourceType.name() +
                File.separator + DateUtil.date2String(new Date(), DateUtil.YYYY_MM_DD);
    }

    private String generateFileName() {
        return UUID.randomUUID().toString();
    }
}
