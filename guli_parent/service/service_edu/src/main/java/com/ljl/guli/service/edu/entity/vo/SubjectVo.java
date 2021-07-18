/**
 * FileName: SubjectVo
 * Author: ljl
 * Date: 2021/6/3 10:41
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectVo implements Serializable {

    private String id;
    private String title;
    private Integer sort;
    private List<SubjectVo> children = new ArrayList<>();

}
