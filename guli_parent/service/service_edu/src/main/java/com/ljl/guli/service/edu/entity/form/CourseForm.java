/**
 * FileName: CourseForm
 * Author: ljl
 * Date: 2021/6/3 12:55
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel("课程信息")
@Data
public class CourseForm implements Serializable {


    @ApiModelProperty("课程ID")
    private String id;

    @ApiModelProperty("课程讲师ID")
    private String teacherId;

    @ApiModelProperty("课程专业ID")
    private String subjectId;

    @ApiModelProperty("课程专业父ID")
    private String subjectParentId;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty("课程总课时")
    private Integer lessonNum;

    @ApiModelProperty("课程封面路径")
    private String cover;

    @ApiModelProperty("课程简介")
    private String description;
}
