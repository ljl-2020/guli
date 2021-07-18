package com.ljl.guli.service.cms.controller.api;



import com.ljl.guli.service.cms.entity.Ad;
import com.ljl.guli.service.cms.service.AdService;
import com.ljl.guli.common.base.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.List;


//@CrossOrigin //解决跨域问题
@Api(tags = "广告推荐")
@RestController
@RequestMapping("/api/cms/ad")
@Slf4j
public class ApiAdController {
    @Autowired
    private AdService adService;

    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("list/{adTypeId}")
    public R listByAdTypeId(
            @ApiParam(value = "推荐位ID", required = true)
            @PathVariable String adTypeId) {
        List<Ad> ads = adService.selectByAdTypeId(adTypeId);
        log.info("获取首页信息："+ads.toString());
        return R.ok().data("items", ads);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    // 在redis中存数据
    @PostMapping("save-test")
    public R saveAd(@RequestBody Ad ad) {
        redisTemplate.opsForValue().set("ad", ad);
        return R.ok();
    }

    // redis查找数据
    @GetMapping("get-test/{key}")
    public R getAd(@PathVariable String key) {
        Ad ad = (Ad) redisTemplate.opsForValue().get("ad");
        return R.ok().data("item", ad);
    }

    //redis删除数据
    @DeleteMapping("delete-test/{key}")
    public R deleteAd(@PathVariable String key) {
        Boolean result = redisTemplate.hasKey("ad");
        if (result) {
            redisTemplate.delete("ad");
        }
        return R.ok();
    }
}
