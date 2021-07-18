package com.ljl.guli.service.trade.feign;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.base.dto.CourseDto;
import com.ljl.guli.service.trade.feign.fallback.EduCourseServiceFallback;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(value = "service-edu",fallback = EduCourseServiceFallback.class)
public interface EduCourseService {
    @ApiOperation(value = "根据课程Id获取数据")
    @GetMapping("/api/edu/course/inner/get-course-dto/{id}")
    CourseDto getCourseDtoById(@ApiParam(value = "课程Id",required = true)
                                      @PathVariable(value = "id") String id);

    @PutMapping("/api/edu/course/inner/update/{id}")
    R updateCourseById(@ApiParam("课程ID") @PathVariable String id);
}
