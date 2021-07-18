package com.ljl.guli.service.edu.controller;


import com.ljl.guli.common.base.result.R;
import com.ljl.guli.common.base.result.ResultCode;
import com.ljl.guli.common.base.utils.ExceptionUtil;
import com.ljl.guli.service.base.exception.MyException;
import com.ljl.guli.service.edu.entity.vo.SubjectVo;
import com.ljl.guli.service.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@RestController
@RequestMapping("/admin/edu/subject")
//@CrossOrigin
@Api("课程分类管理")
@Slf4j
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("Excel批量导入课程分类")
    @PostMapping("import")
    public R batchImport(
            @ApiParam(value = "Excel文件",required = true)
            @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            subjectService.bathImport(inputStream);
            return R.ok().message("导入成功");
        }catch (Exception e){
            log.error(ExceptionUtil.getMessage(e));
            throw new MyException(ResultCode.EXCEL_DATA_ERROR);
        }
    }

    @ApiOperation("课程分类数据列表")
    @GetMapping("nested-list")
    public R nestedList(){
        List<SubjectVo> subjectVos = subjectService.nestedList();
        return R.ok().data("subjectVos",subjectVos);
    }


}

