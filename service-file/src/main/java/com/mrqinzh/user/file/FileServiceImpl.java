package com.mrqinzh.user.file;

import com.mrqinzh.common.entity.MyFile;
import com.mrqinzh.common.enums.FileSourceType;
import com.mrqinzh.common.enums.MimeType;
import com.mrqinzh.user.handler.FileInfo;
import com.mrqinzh.user.handler.GlobalFileHandler;
import com.mrqinzh.user.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private GlobalFileHandler handler;

    @Override
    public String add(byte[] file, MimeType mimeType, FileSourceType fileSourceType) {
        FileInfo info = handler.upload(file, mimeType, fileSourceType);
        // TODO: 2023/3/28 入库
        return info.getFileKey();
    }

    @Override
    public void delete(String fileKey) {
        MyFile dbFile = fileMapper.getByFileName(fileKey);
        // TODO: 2023/3/28 异步删除
        if (dbFile.getFilePlace().equals("本地")) {
            String filePath = dbFile.getFilePath() + File.separator + dbFile.getFileName();
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
        fileMapper.deleteStatus(fileKey);
    }

}
