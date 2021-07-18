/**
 * FileName: ApiMediaController
 * Author: ljl
 * Date: 2021/7/7 15:32
 * Description:
 * History:
 */


package com.ljl.guli.service.vod.controller.api;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.result.ResultCode;
import com.ljl.guli.common.base.utils.ExceptionUtil;
import com.ljl.guli.service.base.exception.MyException;
import com.ljl.guli.service.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "点播")
@RestController
//@CrossOrigin
@RequestMapping("/api/vod/media")
@Slf4j
public class ApiMediaController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("获取视频凭证")
    @GetMapping("get-play-auth/{sourceId}")
    public R getPlayAuth(@ApiParam(value = "id",required = true)
            @PathVariable String sourceId){
        try {
            log.info("获取视频凭证");
            String playAuth = videoService.getPlayAuth(sourceId);
            return R.ok().message("获取播放凭证成功").data("playAuth",playAuth);
        }catch (Exception e){
            log.error(ExceptionUtil.getMessage(e));
            throw new MyException(ResultCode.VIDEO_GET_ERROR);
        }


    }
}
