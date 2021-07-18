/**
 * FileName: ExcelSubjectDataListener
 * Author: ljl
 * Date: 2021/6/3 9:32
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljl.guli.service.edu.entity.Subject;
import com.ljl.guli.service.edu.entity.excel.ExcelSubjectData;
import com.ljl.guli.service.edu.mapper.SubjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {

    private SubjectMapper subjectMapper;

    public ExcelSubjectDataListener(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    public ExcelSubjectDataListener() {
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}",excelSubjectData);
        //处理读取出来的数据
        String levelOneTitle = excelSubjectData.getLevelOneTitle();
        String levelTwoTitle = excelSubjectData.getLevelTwoTitle();
        String parentId = null;
        //一级类别存储
        Subject subjectExits = getByTitle(levelOneTitle) ;
        //组装数据，存入数据库
        if (subjectExits == null){
            Subject subject = new Subject();
            subject.setParentId("0");
            subject.setTitle(levelOneTitle);
            subjectMapper.insert(subject);
            parentId = subject.getId();
        }else{
            parentId = subjectExits.getId();
        }

        //二级类别
        Subject byTitleTwo = this.getByTitleTwo(levelTwoTitle, parentId);
        if(byTitleTwo == null){
            Subject subject = new Subject();
            subject.setParentId(parentId);
            subject.setTitle(levelTwoTitle);
            subjectMapper.insert(subject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部数据解析完成");
    }

    private Subject getByTitle(String title){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        queryWrapper.eq("parent_id","0");
        return subjectMapper.selectOne(queryWrapper);
    }

    private Subject getByTitleTwo(String title,String parentId){
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",title);
        queryWrapper.eq("parent_id",parentId);
        return subjectMapper.selectOne(queryWrapper);
    }
}
