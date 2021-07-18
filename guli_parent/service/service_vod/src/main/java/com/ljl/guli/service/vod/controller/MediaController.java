/**
 * FileName: MediaController
 * Author: ljl
 * Date: 2021/6/16 12:30
 * Description:
 * History:
 */


package com.ljl.guli.service.vod.controller;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.result.ResultCode;
import com.ljl.guli.common.base.utils.ExceptionUtil;
import com.ljl.guli.service.base.exception.MyException;
import com.ljl.guli.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Api(description = "视频上传")
@RestController
//@CrossOrigin
@RequestMapping("/admin/vod/video")
@Slf4j
public class MediaController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(value = "文件名",required = true)
            @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String videoId = videoService.uploadVideo(inputStream, originalFilename);
            return R.ok().message("视频上传成功").data("videoId",videoId);
        } catch (IOException e) {
            log.error(ExceptionUtil.getMessage(e));
            throw new MyException(ResultCode.VIDEO_UPLOAD_ERROR);
        }
    }

    @DeleteMapping("remove/{videoId}")
    public R removeVideo(@ApiParam(value = "视频ID",required = true)
                             @PathVariable String videoId ){
        try {
            videoService.removeVideo(videoId);
            return R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            throw new MyException(ResultCode.VIDEO_DELETE_ERROR);
        }
    }

    @DeleteMapping("remove")
    public R removeVideoByIdList(@ApiParam(value = "视频ID列表",required = true)
                         @RequestBody List<String> videoIdList ){
        try {
            log.info("删除视频");
            videoService.removeVideoByIdList(videoIdList);
            return R.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(ExceptionUtil.getMessage(e));
            throw new MyException(ResultCode.VIDEO_DELETE_ERROR);
        }
    }
}
