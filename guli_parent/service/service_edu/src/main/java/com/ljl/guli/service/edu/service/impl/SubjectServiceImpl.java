package com.ljl.guli.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ljl.guli.service.edu.entity.Subject;
import com.ljl.guli.service.edu.entity.excel.ExcelSubjectData;
import com.ljl.guli.service.edu.entity.vo.SubjectVo;
import com.ljl.guli.service.edu.listener.ExcelSubjectDataListener;
import com.ljl.guli.service.edu.mapper.SubjectMapper;
import com.ljl.guli.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {


    @Override
    public void bathImport(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectDataListener(baseMapper))
                .excelType(ExcelTypeEnum.XLS)
                .sheet().doRead();
    }

    @Override
    public List<SubjectVo> nestedList() {
        return baseMapper.selectNestedListByParentId("0");
    }
}
