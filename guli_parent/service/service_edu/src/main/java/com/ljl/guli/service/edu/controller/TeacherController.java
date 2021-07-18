package com.ljl.guli.service.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.entity.Teacher;
import com.ljl.guli.service.edu.entity.vo.TeacherQueryVo;
import com.ljl.guli.service.edu.feign.OssFileService;
import com.ljl.guli.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
//@CrossOrigin
@Api("讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
@Slf4j
public class TeacherController {


    @Autowired
    private OssFileService ossFileService;


    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取讲师列表")
    @GetMapping("list")
    public R getAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }

    @ApiOperation("根据讲师ID删除")
    @DeleteMapping("remove/{id}")
    public R remove(@ApiParam("讲师ID") @PathVariable String id){
        boolean b1 = teacherService.removeAvatorById(id);
        boolean b = teacherService.removeById(id);

        if(b){
            return R.ok().message("删除成功");
        }
        return R.error().message("无此数据");
    }

    @ApiOperation("分页查询")
    @GetMapping("page/{pa}/{limit}")
    public R pageList(@ApiParam("开始页") @PathVariable Long pa,
                      @ApiParam("每页数据") @PathVariable Long limit,
                      @ApiParam("查询条件")TeacherQueryVo teacherQueryVo){
        Page<Teacher> page  = new Page<Teacher>(pa,limit);
        Page<Teacher> pageList = teacherService.selectPage(page,teacherQueryVo);
        return R.ok().data("total",pageList.getTotal()).data("page",pageList);
    }

    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam("讲师对象") @RequestBody TeacherQueryVo teacher){
        boolean save = teacherService.save(teacher);
        if(save){
            return R.ok().message("保存成功");
        }else{
            return R.ok().message("保存失败");
        }
    }

    @ApiOperation("编辑讲师")
    @PostMapping("update")
    public R update(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        boolean date = teacherService.updateById(teacher);
        if(date){
            return R.ok().message("更新成功");
        }else{
            return R.error().message("更新失败");
        }

    }
    @ApiOperation("获取讲师")
    @GetMapping("get/{id}")
    public R getById(@ApiParam("讲师id")@PathVariable String id){
        Teacher data = teacherService.getById(id);
        if(data != null){
            return R.ok().data("teacher",data);
        }else{
            return R.error().message("查询失败");
        }

    }

    @ApiOperation("根据讲师id列表删除")
    @DeleteMapping("remove-batch")
    public R remove(@ApiParam( value = "讲师ID列表",required = true) @RequestBody List<String> idList){
        boolean b = teacherService.removeByIds(idList);
        if(b){
            return R.ok().message("删除成功");
        }
        return R.error().message("无此数据");
    }

    @ApiOperation("根据关键字查询讲师列表")
    @GetMapping("list/name/{key}")
    public R selectTeacherByKeys(@ApiParam( value = "关键字",required = true) @PathVariable String key){
        List<Map<String, Object>> data = teacherService.selectNameList(key);
        return R.ok().data("nameList",data);
    }

    @ApiOperation("测试并发")
    @GetMapping("concurrent")
    public R test2(){
        log.info("edu 调用oss test_concurrent");
        ossFileService.test();
        return R.ok();
    }

    @ApiOperation("测试宁发")
    @GetMapping("test_concurrent")
    public R test(){
        log.info("test_concurrent");
        return R.ok();
    }


}

