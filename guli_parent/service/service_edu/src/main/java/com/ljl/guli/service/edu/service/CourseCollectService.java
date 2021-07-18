package com.ljl.guli.service.edu.service;

import com.ljl.guli.service.edu.entity.CourseCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljl.guli.service.edu.entity.vo.CourseCollectVo;

import java.util.List;

/**
 * <p>
 * 课程收藏 服务类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
public interface CourseCollectService extends IService<CourseCollect> {

    boolean saveCourse(String courseId, String id);

    boolean removeCollect(String courseId, String id);

    List<CourseCollectVo> getListByMemberId(String id);
}
