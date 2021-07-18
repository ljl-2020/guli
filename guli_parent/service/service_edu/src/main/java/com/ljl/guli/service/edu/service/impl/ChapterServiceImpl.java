package com.ljl.guli.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljl.guli.service.edu.entity.Chapter;
import com.ljl.guli.service.edu.entity.Video;
import com.ljl.guli.service.edu.entity.vo.ChapterVo;
import com.ljl.guli.service.edu.entity.vo.VideoVo;
import com.ljl.guli.service.edu.mapper.ChapterMapper;
import com.ljl.guli.service.edu.mapper.VideoMapper;
import com.ljl.guli.service.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoMapper videoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeChapterById(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("chapter_id",id);
        videoMapper.deleteByMap(map);
        return this.removeById(id);
    }

    @Override
    public List<ChapterVo> nestedList(String id) {
        //组装章节列表：List<ChapterVo>
        //章节信息
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        queryWrapper.orderByAsc("sort","id");
        List<Chapter> chapters = baseMapper.selectList(queryWrapper);
        //可谓是信息
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        videoQueryWrapper.orderByAsc("sort","id");
        List<Video> videos = videoMapper.selectList(videoQueryWrapper);
        List<ChapterVo> chapterVos = new ArrayList<>();
        for(Chapter chapter: chapters){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            List<VideoVo> videoVos = new ArrayList<>();
            for(Video video:videos){
                if(chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);
            chapterVos.add(chapterVo);
        }
        return chapterVos;
    }
}

