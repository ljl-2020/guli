package com.ljl.guli.service.edu.mapper;

import com.ljl.guli.service.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljl.guli.service.edu.entity.vo.SubjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    List<SubjectVo> selectNestedListByParentId(@Param("parentId") String parentId);
}
