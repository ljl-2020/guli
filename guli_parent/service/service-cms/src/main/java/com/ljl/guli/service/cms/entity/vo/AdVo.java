package com.ljl.guli.service.cms.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("广告推荐")
@Data
public class AdVo {
    @ApiModelProperty(value = "广告ID")
    private String id;
    @ApiModelProperty(value = "广告标题")
    private String title;
    @ApiModelProperty(value = "排序")
    private Integer sort;
    @ApiModelProperty(value = "所属分类")
    private String type;
}