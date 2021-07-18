package com.ljl.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljl.guli.service.edu.entity.CourseCollect;
import com.ljl.guli.service.edu.entity.vo.CourseCollectVo;
import com.ljl.guli.service.edu.mapper.CourseCollectMapper;
import com.ljl.guli.service.edu.service.CourseCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程收藏 服务实现类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@Service
public class CourseCollectServiceImpl extends ServiceImpl<CourseCollectMapper, CourseCollect> implements CourseCollectService {

    @Override
    public boolean saveCourse(String courseId, String id) {
        //先查询是否已被收藏
        QueryWrapper<CourseCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",id);
        CourseCollect courseCollect = baseMapper.selectOne(queryWrapper);
        if(courseCollect != null ){
            return true;
        }
        courseCollect.setCourseId(courseId);
        courseCollect.setMemberId(id);
        int insert = baseMapper.insert(courseCollect);
        if(insert <= 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean removeCollect(String courseId, String id) {
        QueryWrapper<CourseCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",id);
        return this.remove(queryWrapper);
    }

    @Override
    public List<CourseCollectVo> getListByMemberId(String id) {
        return baseMapper.selectListByMemberId(id);
    }
}
