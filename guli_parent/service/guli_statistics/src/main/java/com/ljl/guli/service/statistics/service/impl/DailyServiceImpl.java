package com.ljl.guli.service.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.statistics.entity.Daily;
import com.ljl.guli.service.statistics.feign.UcenterMemberService;
import com.ljl.guli.service.statistics.mapper.DailyMapper;
import com.ljl.guli.service.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.apache.velocity.util.ArrayListWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author ljl
 * @since 2021-07-17
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createStatisticsByDay(String day) {
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated",day);
        baseMapper.delete(queryWrapper);
        R r = ucenterMemberService.countRegister(day);
        Integer registerNum = (Integer)r.getData().get("countRegister");

        //远程获取其他统计数据
        int loginNum = RandomUtils.nextInt(100, 1000);
        int videoNum = RandomUtils.nextInt(100, 1000);
        int courseNum = RandomUtils.nextInt(100, 1000);
        Daily daily = new Daily();
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);
        daily.setLoginNum(loginNum);
        daily.setRegisterNum(registerNum);
        daily.setVideoViewNum(videoNum);
        baseMapper.insert(daily);
    }

    @Override
    public Map<String,  Map<String, Object>> getCharData(String begin, String end) {
        Map<String, Object> registerNum = this.getChartDataByType(begin, end, "register_num");
        Map<String, Object> loginNum = this.getChartDataByType(begin, end, "login_num");
        Map<String, Object> videoViewNum = this.getChartDataByType(begin, end, "video_view_num");
        Map<String, Object> courseNum = this.getChartDataByType(begin, end, "course_num");
        HashMap<String, Map<String, Object>> res = new HashMap<>();
        res.put("registerNum",registerNum);
        res.put("loginNum",loginNum);
        res.put("videoViewNum",videoViewNum);
        res.put("courseNum",courseNum);
        return res;
    }

    private Map<String,Object> getChartDataByType(String begin,String end,String type){
        Map<String,Object> map = new HashMap<>();
        List<String> xlist = new ArrayList();
        List<Integer> yList = new ArrayList<>();
        QueryWrapper<Daily> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("date_calculated",type);
        queryWrapper.between("date_calculated",begin,end);

        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        for(Map<String,Object> map1: maps){
            String dateCalculated = (String) map1.get("date_calculated");
            xlist.add(dateCalculated);
            Integer integer = (Integer) map1.get(type);
            yList.add(integer);
        }
        map.put("xData",xlist);
        map.put("yData",yList);
        return map;
    }
}
