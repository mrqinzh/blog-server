package com.mrqinzh.common.domain.enums;

import org.apache.commons.lang3.StringUtils;

public enum MimeType {

    pdf("application/pdf"),
    ofd("application/octet-stream"),
    xlsx("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    pptx("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    docx("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    doc("application/msword"),
    wps("application/msword"),
    xls("application/vnd.ms-excel"),
    ppt("application/vnd.ms-powerpoint"),
    png("image/png"),
    webp("image/webp"),
    tiff("image/tiff"),
    tif("image/tiff"),
    xml("application/xml"),
    xht("application/xhtml+xml"),
    xhtml("application/xhtml+xml"),
    htm("text/html"),
    html("text/html"),
    txt("text/plain"),
    jpg("image/jpeg"),
    jpeg("image/jpeg"),
    gif("image/gif"),
    heic("application/octet-stream"),
    avi("audio/basic"),
    zip("application/zip"),
    rar("application/zip"),
    rtf("application/rtf"),
    css("text/css"),
    mp4("video/mp4"),
    md("text/markdown"),
    json("application/json"),
    other("application/octet-stream");

    // 类型
    private String mimetype;

    MimeType(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    /**
     * 根据文件名获取类型，文件名须包含扩展名
     * @param fileName 文件名（须包含扩展名）
     * @return 类型
     */
    public static MimeType getByFileName(String fileName) {
        if (fileName == null) {
            return null;
        }
        int extIndex = fileName.lastIndexOf(".");
        if (extIndex > 0 && extIndex + 1 < fileName.length()) {
            String ext = fileName.substring(extIndex + 1).toLowerCase();
            try {
                return valueOf(ext);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static boolean isImg(String docType) {
        if(StringUtils.isEmpty(docType)){
            return false;
        }
        switch (docType) {
            case "png":
            case "gif":
            case "jpg":
            case "jpeg":
            case "tiff":
            case "tif":
            case "heic":
                return true;
            default:
                return false;
        }
    }

    public static boolean isWord(String docType) {
        if(StringUtils.isEmpty(docType)){
            return false;
        }
        switch (docType) {
            case "docx":
            case "doc":
            case "wps":
                return true;
            default:
                return false;
        }
    }

    public static boolean isExcel(String docType) {
        if(StringUtils.isEmpty(docType)){
            return false;
        }
        switch (docType) {
            case "xlsx":
            case "xls":
                return true;
            default:
                return false;
        }
    }

    public static boolean isHtml(String docType) {
        if(StringUtils.isEmpty(docType)) {
            return false;
        }
        switch (docType) {
            case "html":
            case "htm":
                return true;
            default:
                return false;
        }
    }

    public static  MimeType getMimeType(String type) {
        if(StringUtils.isBlank(type)) {
            return null;
        }
        //可优化：通过map获取
        MimeType[] mimeTypes = MimeType.values();
        for(MimeType mimeType :mimeTypes) {
            if(mimeType.getMimetype().equals(type)) {
                return mimeType;
            }
        }

        MimeType mimeType = null;
        try {
            mimeType = MimeType.valueOf(type.toLowerCase());
        } catch (Exception e) {
        }
        return mimeType;
    }

    /**
     * 是否能转化为pdf
     * @param type
     * @return
     */
    public static boolean convertPdfable(String type) {
        return convertPdfabke(getMimeType(type));
    }

    /**
     * 是否能转化为pdf
     * @param mimeType
     * @return
     */
    public static boolean convertPdfabke(MimeType mimeType) {
        boolean convertabke = false;
        if(mimeType == null) {
            return convertabke;
        }
        switch (mimeType) {
            case pdf:
            case docx:
            case doc:
            case wps:
            case txt:
            case xls:
            case xlsx:
            case png:
            case jpg:
            case jpeg:
            case gif:
            case rtf:
            case tiff:
            case tif:
            case html:
            case htm:
            case heic:
                convertabke = true;
                break;
            default:
                break;
        }
        return convertabke;
    }

    /**
     * 用印流程支持的本地文件类型
     * @param type
     * @return
     */
    public static boolean isCategorySupport(String type) {
        if (StringUtils.isEmpty(type)) {
            return false;
        }
        switch (type) {
            case "doc":
            case "docx":
            case "wps":
            case "pdf":
            case "xls":
            case "xlsx":
                return true;
            default:
                return false;
        }
    }

    /**
     * 检测图片输出格式是否正确输入 目前仅支持png, jpeg
     * @return
     */
    public static boolean checkImageOutPutType(MimeType imageType) {
        //当前仅支持png与jpeg作为输入 ，jpg格式在ie浏览器不兼容
        return png.equals(imageType) || jpeg.equals(imageType);
    }

    /**
     * 校验文件后缀名
     * @param mimeType
     * @return
     */
    public static boolean checkFileSuffix(MimeType mimeType){
        return mimeType.equals(doc) || mimeType.equals(docx)
                || mimeType.equals(wps) || mimeType.equals(pdf)
                || mimeType.equals(xls) || mimeType.equals(xlsx)
                || mimeType.equals(txt) || mimeType.equals(rtf) || mimeType.equals(ofd);
    }

}
