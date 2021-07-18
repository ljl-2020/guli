/**
 * FileName: ApiSubjectController
 * Author: ljl
 * Date: 2021/7/7 11:41
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.controller.api;


import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.entity.vo.SubjectVo;
import com.ljl.guli.service.edu.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/edu/subject")
@Slf4j
//@CrossOrigin
public class ApiSubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("nested-list")
    public R nestedList(){
        List<SubjectVo> subjectVos = subjectService.nestedList();
        return R.ok().data("items",subjectVos);
    }
}
