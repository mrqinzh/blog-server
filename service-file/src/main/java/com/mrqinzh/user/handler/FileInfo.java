package com.mrqinzh.user.handler;

import com.mrqinzh.common.entity.MyFile;

import java.util.Date;

public class FileInfo {

    private String fileSize;
    private String fileName;
    private String fileType;
    private String fileLink;
    private String folderPath;
    private FileClientType fileClient;

    private String fileKey;

    public MyFile toMyFile() {
        MyFile file = new MyFile();
        file.setFileName(fileName);
        file.setFileType(fileType);
        file.setFileSize(fileSize);
        file.setFilePath(fileLink);
        file.setFilePlace(fileClient.name());
        file.setFileCreateTime(new Date());
        return file;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public FileClientType getFileClient() {
        return fileClient;
    }

    public void setFileClient(FileClientType fileClient) {
        this.fileClient = fileClient;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }
}
