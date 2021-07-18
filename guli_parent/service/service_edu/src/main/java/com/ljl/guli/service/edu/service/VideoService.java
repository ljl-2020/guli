package com.ljl.guli.service.edu.service;

import com.ljl.guli.service.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
public interface VideoService extends IService<Video> {

    void removeMediaVideoById(String id);


    void removeMediaVideoByChapterId(String chapterId);

    void removeMediaVideoByCourseId(String courseId);
}
