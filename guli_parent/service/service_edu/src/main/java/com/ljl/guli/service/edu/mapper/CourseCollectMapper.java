package com.ljl.guli.service.edu.mapper;

import com.ljl.guli.service.edu.entity.CourseCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljl.guli.service.edu.entity.vo.CourseCollectVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程收藏 Mapper 接口
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@Repository
public interface CourseCollectMapper extends BaseMapper<CourseCollect> {

    List<CourseCollectVo> selectListByMemberId(String id);
}
