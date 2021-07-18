/**
 * FileName: ApiCourseController
 * Author: ljl
 * Date: 2021/7/6 20:38
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.controller.api;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.base.dto.CourseDto;
import com.ljl.guli.service.edu.entity.Course;
import com.ljl.guli.service.edu.entity.form.CourseForm;
import com.ljl.guli.service.edu.entity.vo.ChapterVo;
import com.ljl.guli.service.edu.entity.vo.WebCourseQueryVo;
import com.ljl.guli.service.edu.entity.vo.WebCourseVo;
import com.ljl.guli.service.edu.feign.VodMediaService;
import com.ljl.guli.service.edu.service.ChapterService;
import com.ljl.guli.service.edu.service.CourseService;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edu/course")
@Slf4j
//@CrossOrigin
@Api(value = "前端课程展示")
public class ApiCourseController {

    @Autowired
    private VodMediaService vodMediaService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;


    @ApiOperation(value = "分页展示")
    @GetMapping("list")
    public R pageList(
            @ApiParam(value = "查询条件")
                    WebCourseQueryVo webCourseQueryVo
            ){
        List<Course> courseList = courseService.webSelectList(webCourseQueryVo);
        return R.ok().data("courseList",courseList);
    }

    @ApiOperation(value = "根据Id获取数据")
    @GetMapping("get/{id}")
    public R getWebCourseInfo(@ApiParam(value = "课程Id",required = true)
            @PathVariable String id){
        WebCourseVo webCourseVo = courseService.selectWebCourseVoByCourseId(id);
        List<ChapterVo> chapterVos = chapterService.nestedList(id);
        return R.ok().data("items",webCourseVo).data("chapterVoList",chapterVos);
    }

    @ApiOperation(value = "根据课程Id获取数据")
    @GetMapping("inner/get-course-dto/{id}")
    public CourseDto getCourseDtoById(@ApiParam(value = "课程Id",required = true)
                              @PathVariable String id){

        CourseDto courseDto = courseService.getCourseDtoById(id);
        return courseDto;
    }
    @ApiOperation("根据ID发布信息")
    @PutMapping("inner/update/{id}")
    public R updateCourseById(@ApiParam("课程ID") @PathVariable String id){
        boolean result = courseService.updateCourseById(id);
        if(result ){
            return R.ok().message("OK");
        }
        return R.error().message("ERROR");
    }




}
