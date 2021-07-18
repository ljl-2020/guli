package com.ljl.guli.service.statistics.service;

import com.ljl.guli.service.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author ljl
 * @since 2021-07-17
 */
public interface DailyService extends IService<Daily> {

    void createStatisticsByDay(String day);

    Map<String,  Map<String, Object>> getCharData(String begin, String end);
}
