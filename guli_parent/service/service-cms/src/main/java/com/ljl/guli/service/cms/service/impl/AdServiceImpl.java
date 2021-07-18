package com.ljl.guli.service.cms.service.impl;


import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.cms.entity.Ad;
import com.ljl.guli.service.cms.entity.vo.AdVo;
import com.ljl.guli.service.cms.feign.OssFileService;
import com.ljl.guli.service.cms.mapper.AdMapper;
import com.ljl.guli.service.cms.service.AdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author liming
 * @since 2020-07-03
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {
    @Autowired
    private OssFileService ossFileService;

    @Override
    public IPage<AdVo> selectPage(Long page, Long limit) {

        QueryWrapper<AdVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("a.type_id", "a.sort");

        Page<AdVo> pageParam = new Page<>(page, limit);

        List<AdVo> records = baseMapper.selectPageByQueryWrapper(pageParam, queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }

    @Override
    public boolean removeAdImageById(String id) {
        Ad ad = baseMapper.selectById(id);
        if(ad != null) {
            String imagesUrl = ad.getImageUrl();
            if(!StringUtils.isEmpty(imagesUrl)){
                //删除图片
                R r = ossFileService.removeFile(imagesUrl);
                return r.getSuccess();
            }
        }
        return false;
    }

    @Override
    @Cacheable(value = "index", key = "'selectByAdTypeId'")
    public List<Ad> selectByAdTypeId(String adTypeId) {
        QueryWrapper<Ad> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.eq("type_id", adTypeId);
        return baseMapper.selectList(queryWrapper);
    }
}
