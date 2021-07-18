/**
 * FileName: EduCourseServiceFallback
 * Author: ljl
 * Date: 2021/7/14 15:24
 * Description:
 * History:
 */


package com.ljl.guli.service.trade.feign.fallback;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.base.dto.CourseDto;
import com.ljl.guli.service.trade.feign.EduCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class EduCourseServiceFallback implements EduCourseService {
    @Override
    public CourseDto getCourseDtoById(String id) {
        log.info("熔断保护");
        return null;
    }

    @Override
    public R updateCourseById(String id) {
        log.info("熔断保护");
        return null;
    }
}
