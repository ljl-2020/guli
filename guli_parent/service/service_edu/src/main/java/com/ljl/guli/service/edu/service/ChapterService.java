package com.ljl.guli.service.edu.service;

import com.ljl.guli.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljl.guli.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author luojl
 * @since 2021-03-20
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String id);
}
