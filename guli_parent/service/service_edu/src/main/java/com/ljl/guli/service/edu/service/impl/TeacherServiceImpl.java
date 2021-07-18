package com.ljl.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.entity.Course;
import com.ljl.guli.service.edu.entity.Teacher;
import com.ljl.guli.service.edu.entity.vo.TeacherQueryVo;
import com.ljl.guli.service.edu.feign.OssFileService;
import com.ljl.guli.service.edu.mapper.CourseMapper;
import com.ljl.guli.service.edu.mapper.TeacherMapper;
import com.ljl.guli.service.edu.service.CourseService;
import com.ljl.guli.service.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private OssFileService ossFileService;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Page<Teacher> selectPage(Page<Teacher> page,TeacherQueryVo teacherQueryVo) {
        //显示分页列表
        //1.排序，按照sort排序
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if(teacherQueryVo == null){
            return baseMapper.selectPage(page,queryWrapper);
        }

        String name = teacherQueryVo.getName();
        if(!StringUtils.isNullOrEmpty(name)){
            queryWrapper.likeRight("name",name);
        }
        Integer level = teacherQueryVo.getLevel();
        if(level != null){
            queryWrapper.eq("level",level);
        }
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();
        if(!StringUtils.isNullOrEmpty(joinDateBegin)){
            queryWrapper.ge("gmt_create",joinDateBegin.toString());
        }
        if(!StringUtils.isNullOrEmpty(joinDateEnd)){
            queryWrapper.le("gmt_create",joinDateEnd.toString());
        }
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Map<String,Object>> selectNameList(String key) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name");
        queryWrapper.likeRight("name",key);
        return baseMapper.selectMaps(queryWrapper);
    }

    @Override
    public boolean removeAvatorById(String id) {
        //根据id获取讲师的头像地址
        Teacher teacher = baseMapper.selectById(id);
        if(teacher == null){
            return false;
        }
        String avator = teacher.getAvatar();
        if(StringUtils.isNullOrEmpty(avator)){
            return false;
        }
        R r = ossFileService.removeFile(avator);
        return r.getSuccess();
    }

    @Override
    public Map<String, Object> selectTeacherInfoById(String id) {
        Map<String,Object> map = new HashMap<>();
        Teacher teacher = baseMapper.selectById(id);
        //获取课程信息
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id);
        List<Course> courses = courseMapper.selectList(queryWrapper);
        map.put("teacher",teacher);
        map.put("courseList",courses);
        return map;
    }

    @Cacheable(value = "index", key = "'selectHotTeacher'")
    @Override
    public List<Teacher> selectHotTeacher() {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        queryWrapper.last("limit 4");
        return baseMapper.selectList(queryWrapper);
    }
}
