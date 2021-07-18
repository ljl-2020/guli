/**
 * FileName: CourseCollectVo
 * Author: ljl
 * Date: 2021/7/15 11:50
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseCollectVo {

    private String courseId;
    private String courseTitle;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private String gmtCreate;
    private String teacherName;



}
