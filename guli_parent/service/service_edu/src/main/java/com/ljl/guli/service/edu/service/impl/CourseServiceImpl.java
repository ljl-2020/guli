package com.ljl.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.base.dto.CourseDto;
import com.ljl.guli.service.edu.entity.Course;
import com.ljl.guli.service.edu.entity.CourseDescription;
import com.ljl.guli.service.edu.entity.Teacher;
import com.ljl.guli.service.edu.entity.form.CourseForm;
import com.ljl.guli.service.edu.entity.vo.*;
import com.ljl.guli.service.edu.feign.OssFileService;
import com.ljl.guli.service.edu.mapper.*;
import com.ljl.guli.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionMapper descriptionMapper;
    @Autowired
    private OssFileService ossFileService;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CourseCollectMapper courseCollectMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public String saveCourseInfo(CourseForm courseForm) {
        //保存Course
        Course course = new Course();
        BeanUtils.copyProperties(courseForm,course);
        course.setStatus(Course.COURSE_DRAFT);
        baseMapper.insert(course);

        //保存CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseForm.getDescription());
        courseDescription.setId(course.getId());
        descriptionMapper.insert(courseDescription);
        return course.getId();
    }

    @Override
    public CourseForm getCourseInfoById(String courseId) {

        //根据Id获取course
        Course course = baseMapper.selectById(courseId);
        if(course == null){
            return null;
        }
        CourseDescription courseDescription = descriptionMapper.selectById(courseId);
        CourseForm courseForm = new CourseForm();
        BeanUtils.copyProperties(course,courseForm);
        courseForm.setDescription(courseDescription.getDescription());
        //根据Id获取CourseDescription
        return courseForm;
    }

    @Override
    public void updateCourseInfoById(CourseForm courseForm) {
        Course course = new Course();
        BeanUtils.copyProperties(courseForm,course);
        baseMapper.updateById(course);

        //保存CourseDescription
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseForm.getDescription());
        courseDescription.setId(course.getId());
        descriptionMapper.updateById(courseDescription);
    }

    @Override
    public Page<CourseVo> selectPage(Long pa, Long limit, CourseQueryVo courseQueryVo) {
        QueryWrapper<CourseVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");
        String title = courseQueryVo.getTitle();
        String subjectId = courseQueryVo.getSubjectId();
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String teacherId = courseQueryVo.getTeacherId();

        if(!StringUtils.isNullOrEmpty(title)){
            queryWrapper.like("c.title",title);
        }
        if(!StringUtils.isNullOrEmpty(subjectId)){
            queryWrapper.eq("c.subject_id",subjectId);
        }
        if(!StringUtils.isNullOrEmpty(subjectParentId)){
            queryWrapper.eq("c.subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isNullOrEmpty(teacherId)){
            queryWrapper.eq("c.teacher_id",teacherId);
        }
        queryWrapper.eq("c.is_deleted",0);
        Page<CourseVo> courseVoPage = new Page<>(pa,limit);
        List<CourseVo> courseVoList =  baseMapper.selePageByCourseQueryVo(courseVoPage,queryWrapper);
        return courseVoPage.setRecords(courseVoList);
    }

    @Override
    public List<Map<String, Object>> selectNameList(String key) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("title");
        queryWrapper.likeRight("title",key);
        return baseMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean removeCoverById(String id) {
        //根据id获取讲师的头像地址
        Course course = baseMapper.selectById(id);
        if(course == null){
            return false;
        }
        String avator = course.getCover();
        if(StringUtils.isNullOrEmpty(avator)){
            return false;
        }
        R r = ossFileService.removeFile(avator);
        return r.getSuccess();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeCourseById(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("course_id",id);
        descriptionMapper.deleteById(id);
        videoMapper.deleteByMap(map);
        chapterMapper.deleteByMap(map);
        commentMapper.deleteByMap(map);
        courseCollectMapper.deleteByMap(map);

        return this.removeById(id);
    }

    @Override
    public CoursePublishVo getCoursePublishById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishcCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_Normal);
        return this.updateById(course);
    }

    @Override
    public List<Course> webSelectList(WebCourseQueryVo webCourseQueryVo) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        //查询条件拼装
        queryWrapper.orderByAsc();
        queryWrapper.eq("status",Course.COURSE_Normal);
        if(!StringUtils.isNullOrEmpty(webCourseQueryVo.getSubjectParentId())){
            queryWrapper.eq("subject_parent_id", webCourseQueryVo.getSubjectParentId());
        }
        if(!StringUtils.isNullOrEmpty(webCourseQueryVo.getSubjectId())){
            queryWrapper.eq("subject_id", webCourseQueryVo.getSubjectId());
        }
        if(!StringUtils.isNullOrEmpty(webCourseQueryVo.getBuyCountSort())){
            queryWrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isNullOrEmpty(webCourseQueryVo.getGmtCreateSort())){
            queryWrapper.orderByDesc("gmt_create");
        }
        if(!StringUtils.isNullOrEmpty(webCourseQueryVo.getPriceSort())){
            queryWrapper.orderByDesc("price");
        }
        List<Course> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WebCourseVo selectWebCourseVoByCourseId(String id) {
        //浏览数加一
        Course course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount()+1);
        baseMapper.updateById(course);
        return baseMapper.selectWebCourseVoByCourseId(id);
    }
    @Cacheable(value = "index", key = "'selectHotCourse'")
    @Override
    public List<Course> selectHotCourse() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public CourseDto getCourseDtoById(String id) {
//        Course course = baseMapper.selectById(id);
//        String teacherId = course.getTeacherId();
//        Teacher teacher = teacherMapper.selectById(teacherId);
//        CourseDto courseDto = new CourseDto();
//        courseDto.setId(course.getId());
//        courseDto.setCover(course.getCover());
//        courseDto.setPrice(course.getPrice());
//        courseDto.setTeacherName(teacher.getName());
//        courseDto.setTitle(course.getTitle());
//        return courseDto;

        return baseMapper.selectCourseDtoByCourseId(id);
    }

    @Override
    public boolean updateCourseById(String id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Course course = baseMapper.selectOne(queryWrapper);
        course.setBuyCount(course.getBuyCount()+1);
        return this.updateById(course);
    }


}
