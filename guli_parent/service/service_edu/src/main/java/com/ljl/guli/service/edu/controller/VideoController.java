package com.ljl.guli.service.edu.controller;


import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.entity.Chapter;
import com.ljl.guli.service.edu.entity.Video;
import com.ljl.guli.service.edu.feign.VodMediaService;
import com.ljl.guli.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@RestController
@RequestMapping("/admin/edu/video")
//@CrossOrigin
@Api("课时管理")
@Slf4j
public class VideoController {

    @Autowired
    private VideoService videoService;



    @ApiOperation("新增课时")
    @PostMapping("save-video-info")
    public R saveChapter(
            @ApiParam(value = "章节对象",required = true)
            @RequestBody Video video){
        boolean b = videoService.save(video);
        if(b){
            return R.ok().message("保存成功");
        }else{
            return R.error().message("保存失败");
        }

    }
    @ApiOperation("获取课时内容")
    @GetMapping("get/{id}")
    public R getChapterById(
            @ApiParam(value = "章节Id",required = true)
            @PathVariable String id){
        Video video = videoService.getById(id);
        if(video != null){
            return R.ok().data("video",video);
        }else{
            return R.error().message("数据不存在");
        }


    }

    @ApiOperation("更新课时")
    @PutMapping("update")
    public R updateChapterById(
            @ApiParam(value = "章节对象",required = true)
            @RequestBody Video video){
        boolean b = videoService.updateById(video);
        if(b){
            return R.ok().message("更新成功");
        }else{
            return R.error().message("更新失败");
        }

    }

    @ApiOperation("根据Id删除内容")
    @DeleteMapping("remove/{id}")
    public R removeChapterById(
            @ApiParam(value = "章节Id",required = true)
            @PathVariable String id){
        //根据videoId找到视频Id，删除视频信息
        videoService.removeMediaVideoById(id);
        //删除信息
        boolean b = videoService.removeById(id);
        if(b){
            return R.ok().message("删除成功");
        }
        return R.error().message("无此数据");
    }

}

