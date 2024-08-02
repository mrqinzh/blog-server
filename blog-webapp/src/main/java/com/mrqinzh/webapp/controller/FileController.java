package com.mrqinzh.webapp.controller;

import com.mrqinzh.common.enums.RoleType;
import com.mrqinzh.common.resp.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件接口")
@RequestMapping("file")
@RestController
public class FileController {

//    @Autowired
//    private FileService fileService;
//
//    @ApiOperation(value = "添加一个文件到系统中")
//    @PostMapping("add")
//    @AccessPermission(RoleType.SUPER_ADMIN)
//    public Resp add(MultipartFile file) {
//        return fileService.add(file);
//    }
//
//    @ApiOperation(value = "根据文件名删除文件")
//    @DeleteMapping("delete/{fileName}")
//    @AccessPermission(RoleType.SUPER_ADMIN)
//    public Resp delete(@PathVariable String fileName) {
//        return fileService.delete(fileName);
//    }

}
