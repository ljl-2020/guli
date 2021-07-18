package com.ljl.guli.service.statistics.controller;


import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.statistics.feign.UcenterMemberService;
import com.ljl.guli.service.statistics.service.DailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author ljl
 * @since 2021-07-17
 */
@RestController
@RequestMapping("/admin/statistics/daily")
@Api("统计数量")
@Slf4j
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @ApiOperation("生成统计记录 ")
    @PostMapping("create/{day}")
    public R createStatisticsByDay(@PathVariable String day ){
        dailyService.createStatisticsByDay(day);
        return R.ok().message("统计数据生成成功");
    }

    @ApiOperation("显示数据")
    @GetMapping("show-chart/{begin}/{end}")
    public R showChart(@PathVariable String begin,@PathVariable String end){
        Map<String, Map<String, Object>> map = dailyService.getCharData(begin,end);
        return R.ok().data("chartData",map);
    }

}

