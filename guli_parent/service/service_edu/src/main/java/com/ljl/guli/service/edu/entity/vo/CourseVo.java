/**
 * FileName: CourseVo
 * Author: ljl
 * Date: 2021/6/4 12:25
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.vo;

import com.ljl.guli.service.edu.entity.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class CourseVo  implements Serializable {
    private String id;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private String status;

    private Date gmtCreate;

    private String subjectParentTitle;

    private String subjectTitle;

    private String teacherName;



}
