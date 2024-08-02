package com.mrqinzh.user.file;

import com.mrqinzh.common.enums.FileSourceType;
import com.mrqinzh.common.enums.MimeType;

public interface FileService {

    String add(byte[] file, MimeType mimeType, FileSourceType fileSourceType);

    void delete(String fileKey);

}
