package com.mrqinzh.common.vo.email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
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

}