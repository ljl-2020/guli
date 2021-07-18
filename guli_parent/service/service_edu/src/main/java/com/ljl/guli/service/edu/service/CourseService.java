package com.ljl.guli.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljl.guli.service.base.dto.CourseDto;
import com.ljl.guli.service.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljl.guli.service.edu.entity.form.CourseForm;
import com.ljl.guli.service.edu.entity.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseForm courseForm);

    CourseForm getCourseInfoById(String courseId);

    void updateCourseInfoById(CourseForm courseForm);

    Page<CourseVo> selectPage(Long pa, Long limit, CourseQueryVo courseQueryVo);

    List<Map<String, Object>> selectNameList(String key);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishVo getCoursePublishById(String id);

    boolean publishcCourseById(String id);

    List<Course> webSelectList(WebCourseQueryVo webCourseVo);

    WebCourseVo selectWebCourseVoByCourseId(String id);

    List<Course> selectHotCourse();

    CourseDto getCourseDtoById(String id);

    boolean updateCourseById(String id);
}
