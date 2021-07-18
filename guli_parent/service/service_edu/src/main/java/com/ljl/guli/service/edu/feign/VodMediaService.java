package com.ljl.guli.service.edu.feign;


import com.ljl.guli.common.base.result.R;
import com.ljl.guli.service.edu.feign.fallback.VodMediaServiceFallBack;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "service-vod",fallback = VodMediaServiceFallBack.class)
@Service
public interface VodMediaService {

    @DeleteMapping("/admin/vod/video/remove/{videoId}")
    R removeVideo(@PathVariable(value = "videoId") String videoId );
    @DeleteMapping("/admin/vod/video/remove")
    R removeVideoByIdList(@ApiParam(value = "视频ID列表",required = true)
                          @RequestBody List<String> videoIdList );
//    @GetMapping("/api/vod/video/get-play-auth/{sourceId}")
//    public R getPlayAuth(@ApiParam(value = "id",required = true)
//                         @PathVariable String sourceId);
}
