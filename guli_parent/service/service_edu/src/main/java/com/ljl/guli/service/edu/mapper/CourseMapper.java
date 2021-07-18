package com.ljl.guli.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljl.guli.service.base.dto.CourseDto;
import com.ljl.guli.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljl.guli.service.edu.entity.vo.CoursePublishVo;
import com.ljl.guli.service.edu.entity.vo.CourseVo;
import com.ljl.guli.service.edu.entity.vo.WebCourseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> selePageByCourseQueryVo(Page<CourseVo> courseVoPage,@Param(Constants.WRAPPER) QueryWrapper<CourseVo> queryWrapper);

    CoursePublishVo selectCoursePublishVoById(@Param("id") String id);

    WebCourseVo selectWebCourseVoByCourseId(@Param("id") String id);

    CourseDto selectCourseDtoByCourseId(String id);
}
