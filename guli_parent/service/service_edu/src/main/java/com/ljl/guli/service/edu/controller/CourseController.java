package com.ljl.guli.service.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.entity.Course;
import com.ljl.guli.service.edu.entity.Teacher;
import com.ljl.guli.service.edu.entity.form.CourseForm;
import com.ljl.guli.service.edu.entity.vo.CoursePublishVo;
import com.ljl.guli.service.edu.entity.vo.CourseQueryVo;
import com.ljl.guli.service.edu.entity.vo.CourseVo;
import com.ljl.guli.service.edu.entity.vo.TeacherQueryVo;
import com.ljl.guli.service.edu.service.CourseService;
import com.ljl.guli.service.edu.service.VideoService;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
//@CrossOrigin
@Api("课程管理")
@Slf4j
@RestController
@RequestMapping("/admin/edu/course")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private VideoService videoService;

    @ApiOperation("新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(
            @ApiParam(value = "课程信息", required = true)
            @RequestBody CourseForm courseForm) {
        String courseId = courseService.saveCourseInfo(courseForm);
        return R.ok().data("courseId", courseId);
    }

    @ApiOperation("根据courseId获取查询课程")
    @GetMapping("course-info/{id}")
    public R getById(
            @ApiParam(value = "课程Id", required = true)
            @PathVariable String id) {
        if (StringUtils.isNullOrEmpty(id)) {
            return R.error().message("此数据有异常请联系管理人员");
        }
        CourseForm courseForm = courseService.getCourseInfoById(id);
        if (courseForm != null) {
            return R.ok().data("courseForm", courseForm);
        } else {
            return R.error().message("数据不存在");
        }

    }

    @ApiOperation("更新课程")
    @PutMapping("update-course-info")
    public R updateCourseInfo(
            @ApiParam(value = "课程信息", required = true)
            @RequestBody CourseForm courseForm) {
        courseService.updateCourseInfoById(courseForm);
        return R.ok().message("更新成功");
    }

    @ApiOperation("课程分页查询")
    @GetMapping("page/{pa}/{limit}")
    public R pageList(@ApiParam("开始页") @PathVariable Long pa,
                      @ApiParam("每页数据") @PathVariable Long limit,
                      @ApiParam("查询条件") CourseQueryVo courseQueryVo) {
        Page<CourseVo> pageList = courseService.selectPage(pa, limit, courseQueryVo);
        return R.ok().data("total", pageList.getTotal()).data("page", pageList);
    }

    @ApiOperation("根据关键字查询列表")
    @GetMapping("list/name/{key}")
    public R selectCourseByKeys(@ApiParam(value = "关键字", required = true) @PathVariable String key) {
        List<Map<String, Object>> data = courseService.selectNameList(key);
        return R.ok().data("nameList", data);
    }

    @ApiOperation("根据ID删除")
    @DeleteMapping("remove/{id}")
    public R remove(@ApiParam("课程ID") @PathVariable String id) {
        //视频
        videoService.removeMediaVideoByCourseId(id);
        //封面
        boolean b1 = courseService.removeCoverById(id);
        boolean b = courseService.removeCourseById(id);
        if (b) {
            return R.ok().message("删除成功");
        }
        return R.error().message("无此数据");
    }

    @ApiOperation("根据ID获取发布信息")
    @GetMapping("course-publish/{id}")
    public R getCoursePublishById(@ApiParam("课程ID") @PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishById(id);
        if (coursePublishVo != null) {
            return R.ok().data("coursePublishVo", coursePublishVo);
        }
        return R.error().message("无此数据");
    }

    @ApiOperation("根据ID发布信息")
    @PutMapping("publish-course/{id}")
    public R publishcCourseById(@ApiParam("课程ID") @PathVariable String id) {
        boolean result = courseService.publishcCourseById(id);
        if (result) {
            return R.ok().message("发布成功");
        }
        return R.error().message("无此数据");
    }

}

