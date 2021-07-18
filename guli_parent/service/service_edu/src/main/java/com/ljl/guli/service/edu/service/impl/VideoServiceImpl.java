package com.ljl.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljl.guli.service.edu.entity.Video;
import com.ljl.guli.service.edu.feign.VodMediaService;
import com.ljl.guli.service.edu.mapper.VideoMapper;
import com.ljl.guli.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {


    @Autowired
    private VodMediaService vodMediaService;

    @Override
    public void removeMediaVideoById(String id) {
        Video video = baseMapper.selectById(id);
        String videoSourceId = video.getVideoSourceId();
        vodMediaService.removeVideo(videoSourceId);
    }

    @Override
    public void removeMediaVideoByChapterId(String chapterId) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.select("video_source_id");
        videoQueryWrapper.eq("chapter_id",chapterId);
        List<Map<String, Object>> maps = baseMapper.selectMaps(videoQueryWrapper);
        ArrayList<String> ids = new ArrayList<>();
        for(Map<String,Object> map:maps){
            String videoSourceId =(String) map.get("video_source_id");
            ids.add(videoSourceId);
        }
        vodMediaService.removeVideoByIdList(ids);
    }

    @Override
    public void removeMediaVideoByCourseId(String courseId) {
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.select("video_source_id");
        videoQueryWrapper.eq("course_id",courseId);
        List<Map<String, Object>> maps = baseMapper.selectMaps(videoQueryWrapper);
        ArrayList<String> ids = new ArrayList<>();
        for(Map<String,Object> map:maps){
            if(map != null){
                String videoSourceId =(String) map.get("video_source_id");
                if(videoSourceId != null){
                    ids.add(videoSourceId);
                }
            }
        }
        vodMediaService.removeVideoByIdList(ids);
    }
}
