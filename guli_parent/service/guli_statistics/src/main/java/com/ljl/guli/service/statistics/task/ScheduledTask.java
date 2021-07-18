/**
 * FileName: ScheduledTask
 * Author: ljl
 * Date: 2021/7/17 12:09
 * Description:
 * History:
 */


package com.ljl.guli.service.statistics.task;

import com.ljl.guli.service.statistics.service.DailyService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private DailyService dailyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void genStatisticsData(){
        log.info("正在生成统计数据");
        String day = new DateTime().minusDays(1).toString("yyyy-MM-dd");
        dailyService.createStatisticsByDay(day);

    }
}
