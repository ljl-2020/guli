/**
 * FileName: CoursePublishVo
 * Author: ljl
 * Date: 2021/6/15 10:14
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoursePublishVo implements Serializable {

    private String id;

    private String title;

    private String cover;

    private String lessonNum;

    private String subjectParentTitle;

    private String subjectTitle;

    private String teacherName;

    private String price;
}
