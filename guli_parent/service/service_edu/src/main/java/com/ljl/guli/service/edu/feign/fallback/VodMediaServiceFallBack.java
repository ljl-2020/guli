/**
 * FileName: VodMediaServiceFallBack
 * Author: ljl
 * Date: 2021/6/16 17:31
 * Description:
 * History:
 */


package com.ljl.guli.service.edu.feign.fallback;

import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.feign.VodMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VodMediaServiceFallBack implements VodMediaService {
    @Override
    public R removeVideo(String videoId) {
        log.info("熔断保护");
        return R.error();
    }

    @Override
    public R removeVideoByIdList(List<String> videoIdList) {
        log.info("熔断保护");
        return R.error();
    }
}
