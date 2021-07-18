/**
 * FileName: CourseDto
 * Author: ljl
 * Date: 2021/7/14 14:47
 * Description:
 * History:
 */


package com.ljl.guli.service.base.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseDto {

    private String id;
    private String title;
    private BigDecimal price;
    private String cover;
    private String teacherName;
}
