/**
 * FileName: ApiIndexController
 * Author: ljl
 * Date: 2021/7/9 13:10
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.controller.api;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.entity.Course;
import com.ljl.guli.service.edu.entity.Teacher;
import com.ljl.guli.service.edu.service.CourseService;
import com.ljl.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/edu/index")
@Slf4j
//@CrossOrigin
@Api(value = "前端首页信息展示")
public class ApiIndexController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("首页信息")
    @GetMapping("get")
    public R index(){
        List<Course> courseList = courseService.selectHotCourse();
        List<Teacher> teacherList = teacherService.selectHotTeacher();
        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
