/**
 * FileName: ApiTeacherController
 * Author: ljl
 * Date: 2021/6/17 20:08
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.controller.api;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.entity.Teacher;
import com.ljl.guli.service.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/edu/teacher")
@Slf4j
//@CrossOrigin
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取讲师列表")
    @GetMapping("list")
    public R getAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }

    @ApiOperation("获取讲师")
    @GetMapping("get/{id}")
    public R getById(@ApiParam("讲师id")@PathVariable String id){
        Map<String, Object> map = teacherService.selectTeacherInfoById(id);
        if(map != null && map.size() != 0){
            return R.ok().data("teacher",map.get("teacher")).data("courseList",map.get("courseList"));
        }else{
            return R.error().message("查询失败");
        }

    }
}
