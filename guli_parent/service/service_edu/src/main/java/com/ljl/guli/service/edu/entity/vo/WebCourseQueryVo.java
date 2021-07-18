/**
 * FileName: WebCourseVo
 * Author: ljl
 * Date: 2021/7/6 20:35
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WebCourseQueryVo implements Serializable {

    private String subjectParentId;

    private String subjectId;

    private String buyCountSort;

    private String gmtCreateSort;

    private String priceSort;
}
