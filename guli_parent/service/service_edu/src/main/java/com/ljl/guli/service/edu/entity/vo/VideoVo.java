/**
 * FileName: VideoVo
 * Author: ljl
 * Date: 2021/6/15 13:06
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.entity.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class VideoVo implements Serializable {

    private String id;

    private String title;

    private Integer isFree;

    private Integer sort;

    private String videoSourceId;
}
