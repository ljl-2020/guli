/**
 * FileName: CourseQueryVo
 * Author: ljl
 * Date: 2021/6/4 12:22
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQueryVo implements Serializable {

    private String title;

    private String teacherId;

    private String subjectParentId;

    private String subjectId;
}
