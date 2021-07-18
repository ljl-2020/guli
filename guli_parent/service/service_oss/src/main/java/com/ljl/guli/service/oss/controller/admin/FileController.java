/**
 * FileName: FileController
 * Author: ljl
 * Date: 2021/5/15 12:03
 * Description:
 * History:
 */


package com.ljl.guli.service.oss.controller.admin;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.result.ResultCode;
import com.ljl.guli.common.base.utils.ExceptionUtil;
import com.ljl.guli.service.base.exception.MyException;
import com.ljl.guli.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Api(description = "文件上传")
@RestController
//@CrossOrigin
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(value = "文件" ,required = true) @RequestParam("file") MultipartFile file,
            @ApiParam(value = "模块" ,required = true) @RequestParam("module") String module) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFileName = file.getOriginalFilename();
            String url = fileService.upload(inputStream,module,originalFileName);
            return R.ok().data("url",url);
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            throw new MyException(ResultCode.OSS_UPLOAD_ERROR);
        }

    }

    @ApiOperation("删除头像")
    @DeleteMapping("remove")
    public R removeFile(@ApiParam(value = "删除的路径",required = true) @RequestBody String url) {
        fileService.removeFile(url);
        return R.ok().message("文件删除成功");
    }

    @ApiOperation("测试并发")
    @GetMapping("concurrent")
    public R test2(){
        log.info("调用oss test_concurrent");
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return R.ok();
    }

}
