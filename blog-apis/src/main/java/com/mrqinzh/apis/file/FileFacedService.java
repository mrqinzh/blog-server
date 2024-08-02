package com.mrqinzh.apis.file;

import com.mrqinzh.common.enums.FileSourceType;
import com.mrqinzh.common.enums.MimeType;
import com.mrqinzh.common.exception.FileServiceException;

public interface FileFacedService {

    String upload(byte[] fileBytes, MimeType mimeType, FileSourceType dataTypeEnum) throws FileServiceException;

    byte[] download(String fileKey);

}
