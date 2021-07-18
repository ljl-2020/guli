package com.ljl.guli.service.edu.service;

import com.ljl.guli.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljl.guli.service.edu.entity.vo.SubjectVo;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
public interface SubjectService extends IService<Subject> {

    void bathImport(InputStream inputStream);

    List<SubjectVo> nestedList();
}
