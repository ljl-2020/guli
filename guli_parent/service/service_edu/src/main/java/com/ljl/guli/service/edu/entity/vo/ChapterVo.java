/**
 * FileName: ChapterVo
 * Author: ljl
 * Date: 2021/6/15 13:06
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterVo implements Serializable {

    private String id;

    private String title;

    private Integer sort;

    private List<VideoVo> children = new ArrayList<>();
}
