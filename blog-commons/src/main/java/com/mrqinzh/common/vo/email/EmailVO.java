package com.mrqinzh.common.vo.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "邮件信息类", description = "前端传入要发送的邮件信息")
public class EmailVO {

    @ApiModelProperty(value = "邮件主题")
    @NotBlank
    private String emailTitle;

    @ApiModelProperty(value = "邮件内容")
    @NotBlank
    private String emailContent;

    @ApiModelProperty(value = "收件人")
    @NotBlank
    private String to;

    private String filePath;

    public String getEmailTitle() {
        return emailTitle;
    }

    public void setEmailTitle(String emailTitle) {
        this.emailTitle = emailTitle;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}