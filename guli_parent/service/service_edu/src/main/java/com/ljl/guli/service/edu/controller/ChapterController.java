package com.ljl.guli.service.edu.controller;


import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.entity.Chapter;
import com.ljl.guli.service.edu.entity.form.CourseForm;
import com.ljl.guli.service.edu.entity.vo.ChapterVo;
import com.ljl.guli.service.edu.feign.VodMediaService;
import com.ljl.guli.service.edu.service.ChapterService;
import com.ljl.guli.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@RestController
@RequestMapping("/admin/edu/chapter")
//@CrossOrigin
@Api("章节管理")
@Slf4j
public class ChapterController {

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private VideoService videoService;

    @ApiOperation("新增章节")
    @PostMapping("save-chapter-info")
    public R saveChapter(
            @ApiParam(value = "章节对象",required = true)
            @RequestBody Chapter chapter){
        boolean b = chapterService.save(chapter);
        if(b){
            return R.ok().message("保存成功");
        }else{
            return R.error().message("保存失败");
        }

    }

    @ApiOperation("获取章节内容")
    @GetMapping("get/{id}")
    public R getChapterById(
            @ApiParam(value = "章节Id",required = true)
            @PathVariable String id){
        Chapter chapter = chapterService.getById(id);
        if(chapter != null){
            return R.ok().data("chapter",chapter);
        }else{
            return R.error().message("数据不存在");
        }


    }

    @ApiOperation("更新章节")
    @PutMapping("update")
    public R updateChapterById(
            @ApiParam(value = "章节对象",required = true)
            @RequestBody Chapter chapter){
        boolean b = chapterService.updateById(chapter);
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
        //删除视频
        videoService.removeMediaVideoByChapterId(id);

        boolean b = chapterService.removeChapterById(id);
        if(b){
            return R.ok().message("删除成功");
        }
        return R.error().message("无此数据");
    }


    @ApiOperation("嵌套章节列表")
    @GetMapping("nested-list/{courseId}")
    public R nestedListChapterById(
            @ApiParam(value = "章节Id",required = true)
            @PathVariable String courseId){
        List<ChapterVo> chapterVos = chapterService.nestedList(courseId);
        return R.ok().data("chapterVos",chapterVos);
    }


}

