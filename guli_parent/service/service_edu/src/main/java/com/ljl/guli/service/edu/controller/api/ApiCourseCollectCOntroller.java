/**
 * FileName: ApiCourseCollectCOntroller
 * Author: ljl
 * Date: 2021/7/15 11:49
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.controller.api;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.utils.JwtInfo;
import com.ljl.guli.common.base.utils.JwtUtil;
import com.ljl.guli.service.edu.entity.CourseCollect;
import com.ljl.guli.service.edu.entity.vo.CourseCollectVo;
import com.ljl.guli.service.edu.service.CourseCollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/edu/course-collect")
//@CrossOrigin //跨域
@Api("课程收藏管理")
@Slf4j
public class ApiCourseCollectCOntroller {

    @Autowired
    private CourseCollectService courseCollectService;

    @ApiOperation("收藏")
    @PostMapping("save/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request){
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(request);
        boolean save = courseCollectService.saveCourse(courseId,jwtInfo.getId());
        if(save){
            return R.ok().message("收藏成功");
        }else{
            return R.ok().message("收藏失败");
        }
    }

    @ApiOperation("移出收藏")
    @DeleteMapping("remove/{courseId}")
    public R removeCollect(@PathVariable String courseId, HttpServletRequest request){
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(request);
        boolean save = courseCollectService.removeCollect(courseId,jwtInfo.getId());
        if(save){
            return R.ok().message("移出收藏成功");
        }else{
            return R.ok().message("移出收藏失败");
        }
    }

    @ApiOperation("收藏列表")
    @GetMapping("list")
    public R list(HttpServletRequest request){
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(request);
        List<CourseCollectVo> courseCollectVos = courseCollectService.getListByMemberId(jwtInfo.getId());
       return R.ok().data("items",courseCollectVos);
    }
}
