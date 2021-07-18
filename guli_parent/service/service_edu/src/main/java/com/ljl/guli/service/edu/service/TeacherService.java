package com.ljl.guli.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljl.guli.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljl.guli.service.edu.entity.vo.TeacherQueryVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
public interface TeacherService extends IService<Teacher> {

    Page<Teacher> selectPage(Page<Teacher> page, TeacherQueryVo teacherQueryVo);

    List<Map<String,Object>> selectNameList(String key);

    boolean removeAvatorById(String id);

    Map<String,Object> selectTeacherInfoById(String id);

    List<Teacher> selectHotTeacher();
}
